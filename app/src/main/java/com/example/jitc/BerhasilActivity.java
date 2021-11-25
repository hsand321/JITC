package com.example.jitc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jitc.UI.MainActivity;

public class BerhasilActivity extends AppCompatActivity {
    long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil);


    }

    @Override
    public void onBackPressed() {

          startActivity(new Intent(BerhasilActivity.this, MainActivity.class));

    }
}