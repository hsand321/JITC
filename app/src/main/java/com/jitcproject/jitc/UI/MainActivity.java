package com.jitcproject.jitc.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import com.jitcproject.jitc.Fragment.AboutFragment;
import com.jitcproject.jitc.Fragment.HomeFragment;
import com.jitcproject.jitc.Fragment.PendaftaranFragment;
import com.jitcproject.jitc.Fragment.VideoFragment;
import com.example.jitc.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ChipNavigationBar BottomNav;
    private Fragment fragment = null;
    FragmentManager fragmentManager;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        BottomNav = findViewById(R.id.bottomBar);
        if (savedInstanceState == null) {
            BottomNav.setItemSelected(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, homeFragment)
                    .commit();
        }
        BottomNav.setOnItemSelectedListener(i -> {
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
    @Override
    public void onBackPressed() {

        finishAffinity();

    }
}