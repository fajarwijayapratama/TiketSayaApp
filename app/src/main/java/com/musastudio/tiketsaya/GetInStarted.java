package com.musastudio.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class GetInStarted extends AppCompatActivity {

    Animation top_to_bottom, btt;
    Button btn_sign_in;
    Button btn_new_account_create;
    ImageView emblem_app;
    TextView intro_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_in_started);

        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom);
        btt = AnimationUtils.loadAnimation(this,R.anim.btt);

        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_new_account_create = findViewById(R.id.btn_new_account_create);
        emblem_app = findViewById(R.id.emblem_app);
        intro_app = findViewById(R.id.intro_app);

        emblem_app.startAnimation(top_to_bottom);
        intro_app.startAnimation(top_to_bottom);
        btn_sign_in.startAnimation(btt);
        btn_new_account_create.startAnimation(btt);

        btn_new_account_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocreateone = new Intent(GetInStarted.this, RegisterOneAct.class);
                startActivity(gotocreateone);
            }
        });

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosign = new Intent(GetInStarted.this, SignIn.class);
                startActivity(gotosign);
            }
        });
    }
}
