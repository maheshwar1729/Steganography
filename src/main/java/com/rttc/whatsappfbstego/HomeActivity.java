package com.rttc.whatsappfbstego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {

public void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);

 setContentView(R.layout.home);


 Button btn_h = (Button)findViewById(R.id.btn_hide);
 Button btn_uh = (Button)findViewById(R.id.btn_unhide);
    Button btn_hi = (Button)findViewById(R.id.btn_img);
    Button btn_uhi = (Button)findViewById(R.id.btn_unhideImg);
    Button btn_gp = (Button)findViewById(R.id.btn_gp);
    Button btn_ha = (Button)findViewById(R.id.btn_audio);
    Button btn_uha = (Button)findViewById(R.id.btn_unhideAudio);

btn_h.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent ih = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(ih);
    }
});

btn_uh.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent iuh = new Intent(HomeActivity.this,UnHideActivity.class);
        startActivity(iuh);
    }
});

    btn_gp.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent igp = new Intent(HomeActivity.this,NewGrPassword.class);
            startActivity(igp);
        }
    });

    btn_hi.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent igp = new Intent(HomeActivity.this,MainActivityImage.class);
            startActivity(igp);
        }
    });

    btn_uhi.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent igp = new Intent(HomeActivity.this,UnHideActivityImage.class);
            startActivity(igp);
        }
    });

    btn_ha.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent igp = new Intent(HomeActivity.this,  MainActivityAudio.class);
            startActivity(igp);
        }
    });

    btn_uha.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent igp = new Intent(HomeActivity.this,UnHideActivityAudio.class);
            startActivity(igp);
        }
    });


}
public void onBackPressed(){
    return;
}
}
