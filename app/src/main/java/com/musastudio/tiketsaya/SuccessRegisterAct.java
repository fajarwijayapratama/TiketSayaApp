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

public class SuccessRegisterAct extends AppCompatActivity {

    Animation app_splash, btt, top_to_bottom;
    Button btn_explore;
    ImageView icon_success;
    TextView app_title, app_subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        app_title = findViewById(R.id.app_title);
        app_subtitle = findViewById(R.id.app_subtitle);
        icon_success = findViewById(R.id.icon_success);
        btn_explore = findViewById(R.id.btn_explore);

        app_splash = AnimationUtils.loadAnimation(this,R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this,R.anim.btt);
        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom);

        icon_success.startAnimation(app_splash);
        btn_explore.startAnimation(btt);
        app_title.startAnimation(top_to_bottom);
        app_subtitle.startAnimation(top_to_bottom);


        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(SuccessRegisterAct.this, HomeAct.class);
                startActivity(gotohome);
            }
        });

    }
}
