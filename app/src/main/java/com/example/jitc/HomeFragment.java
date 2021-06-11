package com.example.jitc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HomeFragment extends Fragment {
    ImageView database, office, desain_grafis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        database = view.findViewById(R.id.database);
        office = view.findViewById(R.id.office);
        desain_grafis = view.findViewById(R.id.desain_grafis);
//        database.setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), DatabaseActivity.class);
//            startActivity(intent);
//        });
        office.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OfficeActivity.class);
            startActivity(intent);
        });
        desain_grafis.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DesainGrafisActivity.class);
            startActivity(intent);
        });
    }
}