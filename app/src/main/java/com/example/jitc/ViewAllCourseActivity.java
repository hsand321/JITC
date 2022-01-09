package com.example.jitc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jitc.Adapter.AllCourseAdapter;
import com.example.jitc.Adapter.CourseAdapter;
import com.example.jitc.Model.UploadCourseData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAllCourseActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView allRecyclerView;
    private ArrayList<UploadCourseData> list = new ArrayList<>();
    private AllCourseAdapter adapter;

    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_course);
//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        allRecyclerView = (RecyclerView) findViewById(R.id.allRV);

        reference = FirebaseDatabase.getInstance().getReference().child("Pelatihan");

        allRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        allRecyclerView.setHasFixedSize(true);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(false);
                getAllCourse();
//            }
//        });

    }

    private void getAllCourse() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UploadCourseData data = snapshot.getValue(UploadCourseData.class);
                    list.add(data);
                }

                adapter = new AllCourseAdapter(getBaseContext(), list);

                adapter.notifyDataSetChanged();
                allRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}