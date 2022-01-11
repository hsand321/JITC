package com.jitcproject.jitc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jitcproject.jitc.Adapter.CourseAdapter;
import com.jitcproject.jitc.Model.UploadCourseData;
import com.example.jitc.R;
import com.jitcproject.jitc.Course.DatabaseActivity;
import com.jitcproject.jitc.Course.DesainGrafisActivity;
import com.jitcproject.jitc.Course.ModellingActivity;
import com.jitcproject.jitc.Course.MultimediaActivity;
import com.jitcproject.jitc.Course.OfficeActivity;
import com.jitcproject.jitc.Course.OsNetworkActivity;
import com.jitcproject.jitc.Course.PemogramanActivity;
import com.jitcproject.jitc.Course.RequestActivity;
import com.jitcproject.jitc.Course.WebActivity;
import com.jitcproject.jitc.ViewAllCourseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ImageView database, office, desain_grafis, web, network, pemograman, multimedia, modelling, request;
    TextView viewall;
    private RecyclerView viewCourseRV;
    private ProgressBar progressBar;
    private ArrayList <UploadCourseData> list = new ArrayList<>();
    private CourseAdapter adapter;

    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewCourseRV = view.findViewById(R.id.viewCourseRV);
        progressBar = view.findViewById(R.id.progressBar);

        reference = FirebaseDatabase.getInstance().getReference().child("Pelatihan");

        viewCourseRV.setLayoutManager(new LinearLayoutManager(getContext()));
        viewCourseRV.setHasFixedSize(true);

        getCourse();
        return view;
    }

    private void getCourse() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UploadCourseData data = snapshot.getValue(UploadCourseData.class);
                    list.add(data);
                }

                adapter = new CourseAdapter(getActivity(), list);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                viewCourseRV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
        viewall = view.findViewById(R.id.viewall);
        viewall.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ViewAllCourseActivity.class);
            startActivity(intent);
        });
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