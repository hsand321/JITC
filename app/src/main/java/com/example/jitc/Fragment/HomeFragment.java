package com.example.jitc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.jitc.R;
import com.example.jitc.Course.DatabaseActivity;
import com.example.jitc.Course.DesainGrafisActivity;
import com.example.jitc.Course.ModellingActivity;
import com.example.jitc.Course.MultimediaActivity;
import com.example.jitc.Course.OfficeActivity;
import com.example.jitc.Course.OsNetworkActivity;
import com.example.jitc.Course.PemogramanActivity;
import com.example.jitc.Course.RequestActivity;
import com.example.jitc.Course.WebActivity;

public class HomeFragment extends Fragment {

    ImageView database, office, desain_grafis, web, network, pemograman, multimedia, modelling, request;

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
        web = view.findViewById(R.id.web);
        network = view.findViewById(R.id.network);
        pemograman = view.findViewById(R.id.program);
        multimedia = view.findViewById(R.id.multimedia);
        modelling = view.findViewById(R.id.modelling);
        request = view.findViewById(R.id.request);

        database.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DatabaseActivity.class);
            startActivity(intent);
        });
        office.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OfficeActivity.class);
            startActivity(intent);
        });
        desain_grafis.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DesainGrafisActivity.class);
            startActivity(intent);
        });
        web.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            startActivity(intent);
        });
        network.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OsNetworkActivity.class);
            startActivity(intent);
        });
        pemograman.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PemogramanActivity.class);
            startActivity(intent);
        });
        multimedia.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MultimediaActivity.class);
            startActivity(intent);
        });
        modelling.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ModellingActivity.class);
            startActivity(intent);
        });
        request.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RequestActivity.class);
            startActivity(intent);
        });
    }
}