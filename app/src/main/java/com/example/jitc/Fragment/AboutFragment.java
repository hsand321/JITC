package com.example.jitc.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jitc.R;

public class AboutFragment extends Fragment {

    ImageButton maps;
    CardView wa, telegram, instagram, facebook, email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        maps = view.findViewById(R.id.maps);
        wa = view.findViewById(R.id.wa);
        telegram = view.findViewById(R.id.telegram);
        instagram = view.findViewById(R.id.instagram);
        facebook = view.findViewById(R.id.facebook);
        email = view.findViewById(R.id.email);

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/maps/place/Jl.+Candi+Gebang,+Jetis,+Wedomartani,+Kec.+Ngemplak,+Kabupaten+Sleman,+Daerah+Istimewa+Yogyakarta+55584/@-7.7483631,110.4119603,56m/data=!3m1!1e3!4m8!1m2!2m1!1sRuko+2,+JL.+Candi+Gebang+1,+Sleman,+Yogyakarta,+DI+Yogyakarta,+Indonesia!3m4!1s0x2e7a59772460fa9b:0xc50c490c4256cc76!8m2!3d-7.7483231!4d110.4118834");
            }
        });
        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://api.whatsapp.com/send/?phone=%2B6287839953366&text&app_absent=0");
            }
        });
        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://telegram.me/jitc_yk");
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.instagram.com/jitc_yk/");
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://web.facebook.com/infojitc");
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jitcyk.official@gmail.com"});
                intent.putExtra(Intent.EXTRA_CC, new String[] {""});
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(intent, "Ingin Mengirim Email?"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "Tidak ada aplikasi yang mendukung", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void gotoUrl(String string) {
        Uri uri = Uri.parse(string);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}