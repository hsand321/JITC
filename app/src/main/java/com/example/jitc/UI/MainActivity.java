package com.example.jitc.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.jitc.Fragment.AboutFragment;
import com.example.jitc.Fragment.HomeFragment;
import com.example.jitc.Fragment.PendaftaranFragment;
import com.example.jitc.Fragment.VideoFragment;
import com.example.jitc.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment = null;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ChipNavigationBar chipNavigationBar = findViewById(R.id.bottomBar);

        chipNavigationBar.setItemSelected(R.id.nav_home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(i -> {
            switch (i) {
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.nav_video:
                    fragment = new VideoFragment();
                    break;
                case R.id.nav_pendaftaran:
                    fragment = new PendaftaranFragment();
                    break;
                case R.id.nav_about:
                    fragment = new AboutFragment();
                    break;
            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });
    }
}