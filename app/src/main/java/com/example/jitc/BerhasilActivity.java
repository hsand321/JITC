package com.example.jitc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.jitc.UI.MainActivity;

public class BerhasilActivity extends AppCompatActivity {
    long back_pressed;
    Button Home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Home= findViewById(R.id.backhome);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerhasilActivity.this,MainActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {

          startActivity(new Intent(BerhasilActivity.this, MainActivity.class));

    }
}