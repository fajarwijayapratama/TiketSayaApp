package com.musastudio.tiketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOneAct extends AppCompatActivity {

    LinearLayout btn_back;
    Button btn_continue;
    EditText username, password, email_address;
    DatabaseReference reference, reference_username;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email_address = findViewById(R.id.email_address);
        btn_back = findViewById(R.id.btn_back);
        btn_continue = findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // loading button continue
                btn_continue.setEnabled(false);
                btn_continue.setText("Loading...");

                // mengambil username yang sudah tersedia
                reference_username = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(username.getText().toString());
                reference_username.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // if username is exist
                        if (dataSnapshot.exists()){
                            Toast.makeText(getApplicationContext(), "Username Sudah Ada!", Toast.LENGTH_SHORT).show();
                            // loading button continue activated
                            btn_continue.setEnabled(true);
                            btn_continue.setText("Continue");
                        }
                        else {
                            // menyimpan ke local storage (handphone)
                            SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(username_key, username.getText().toString());
                            editor.apply();

                            reference = FirebaseDatabase.getInstance().getReference()
                                    .child("Users").child(username.getText().toString());
                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                                    dataSnapshot.getRef().child("password").setValue(password.getText().toString());
                                    dataSnapshot.getRef().child("email_address").setValue(email_address.getText().toString());
                                    dataSnapshot.getRef().child("user_balance").setValue(800);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                // berpindah activity
                Intent gotonextregister = new Intent(RegisterOneAct.this, RegisterTwoAct.class);
                startActivity(gotonextregister);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
}
