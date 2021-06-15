package com.example.jitc.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.jitc.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public class MultimediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.multimedia);
        Palette.from(bitmap).generate(palette -> {
            if (palette !=null){
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
            }
        });
    }
}