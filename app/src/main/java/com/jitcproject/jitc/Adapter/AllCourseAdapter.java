package com.jitcproject.jitc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jitcproject.jitc.Model.UploadCourseData;
import com.example.jitc.R;
import com.jitcproject.jitc.UI.DetailCourseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllCourseAdapter extends RecyclerView.Adapter<AllCourseAdapter.AllCourseViewAdapter> {
    private Context context;
    private final ArrayList<UploadCourseData> list;

    public AllCourseAdapter(Context context, ArrayList<UploadCourseData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public AllCourseAdapter.AllCourseViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_list_item,parent,false);
        return new AllCourseViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCourseAdapter.AllCourseViewAdapter holder, int position) {
        UploadCourseData currentItem = list.get(position);
        holder.coursejudul.setText(currentItem.getTitle());
        holder.courseharga.setText(currentItem.getHarga());
        holder.coursedurasi.setText(currentItem.getDurasi());
        holder.deskripsic.setText(currentItem.getDeskripsi());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailCourseActivity.class);
                intent.putExtra("title", currentItem.getTitle());
                intent.putExtra("harga", currentItem.getHarga());
                intent.putExtra("durasi", currentItem.getDurasi());
                intent.putExtra("descdetail", currentItem.getDeskripsi());
                intent.putExtra("imagess", currentItem.getImage());
                context.startActivity(intent);
            }
        });

        try{
            if (currentItem.getImage()!=null)
                Picasso.get().load(currentItem.getImage()).into(holder.courseimage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AllCourseViewAdapter extends RecyclerView.ViewHolder {
        private TextView coursejudul;
        private TextView coursedurasi;
        private TextView courseharga;
        private TextView deskripsic;
        private ImageView courseimage;

        View view;
        public AllCourseViewAdapter(@NonNull View itemView) {
            super(itemView);
            coursejudul = itemView.findViewById(R.id.namacourse);
            coursedurasi = itemView.findViewById(R.id.durasi_Course);
            courseharga = itemView.findViewById(R.id.harga_course);
            courseimage = itemView.findViewById(R.id.courseimage);
            deskripsic = itemView.findViewById(R.id.deskripsicourse);
            view = itemView;
        }
    }
}


