package com.jitcproject.jitc.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jitc.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailCourseActivity extends AppCompatActivity {

    ImageView cimage;
    TextView ctitle;
    TextView charga;
    TextView detaildeskripsi;
    TextView cdurasi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cimage = findViewById(R.id.imagess);
        ctitle = findViewById(R.id.dnamacourse);
        charga = findViewById(R.id.dharga);
        cdurasi = findViewById(R.id.ddurasi);
        detaildeskripsi = findViewById(R.id.textdes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String harga = intent.getStringExtra("harga");
        String durasi = intent.getStringExtra("durasi");
        String deskripsidetail = intent.getStringExtra("descdetail");

        String Url =  intent.getStringExtra("imagess");
        Picasso.get().load(Url).into(cimage);

        ctitle.setText(title);
        charga.setText(harga);
        cdurasi.setText(durasi);
        detaildeskripsi.setText(deskripsidetail);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}