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

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewAdapter> {

    private Context context;
    private final ArrayList<UploadCourseData> list;

    public CourseAdapter(Context context, ArrayList<UploadCourseData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_list_item,parent,false);
        return new CourseViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewAdapter holder, int position) {
        int maxCount = 5;
        if(position >= maxCount){
            holder.itemView.setVisibility(View.INVISIBLE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0,0));
        }else{
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
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
    public void viewAll(){

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CourseViewAdapter extends RecyclerView.ViewHolder {
//        private final LinearLayout layout;
//        final LinearLayout.LayoutParams params;
        private TextView coursejudul;
        private TextView coursedurasi;
        private TextView courseharga;
        private TextView deskripsic;
        private ImageView courseimage;

        View view;

        public CourseViewAdapter(@NonNull  View itemView) {
            super(itemView);
//            layout =(LinearLayout)itemView.findViewById(R.id.pelatihanitem);
//            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
            coursejudul = itemView.findViewById(R.id.namacourse);
            coursedurasi = itemView.findViewById(R.id.durasi_Course);
            courseharga = itemView.findViewById(R.id.harga_course);
            courseimage = itemView.findViewById(R.id.courseimage);
            deskripsic = itemView.findViewById(R.id.deskripsicourse);
            view = itemView;
        }
        private void Layout_hide() {
//            params.height = 0;
//            //itemView.setLayoutParams(params); //This One.
//            layout.setLayoutParams(params);   //Or This one.

        }
    }
}
