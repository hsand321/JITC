package com.example.jitc.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jitc.Model.Pendaftaran;
import com.example.jitc.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PendaftaranFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ImageView addImage, profile;
    private EditText nama, email, nohp, asalkampus;
    private ImageButton daftar;
    Pendaftaran pendaftaran;
    private Spinner spinner;
    private String item;
    String[] training={"Pilih","Training1","Traomomg2"};
    private RadioButton laki, perempuan;
    private final int REQ = 1;
    private Bitmap bitmap;
    private DatabaseReference reference;
    private StorageReference storageReference;
    String dowloadUrl = "";
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pendaftaran, container, false);
        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
//Spinner
        spinner= view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        pendaftaran = new Pendaftaran();
        ArrayAdapter arrayAdapter= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item,training);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner.setAdapter(arrayAdapter);
        //Spinner
        pd = new ProgressDialog(getActivity());

        addImage = view.findViewById(R.id.addImage);
        profile = view.findViewById(R.id.profile);
        nama = view.findViewById(R.id.nama);


        laki = view.findViewById(R.id.lakilaki);
        perempuan = view.findViewById(R.id.perempuan);
//        jeniskelamin = view.findViewById(R.id.jeniskelamin);
        email = view.findViewById(R.id.email);
        nohp = view.findViewById(R.id.nohp);
        asalkampus = view.findViewById(R.id.asalkampus);
//        course = view.findViewById(R.id.course);
        daftar = view.findViewById(R.id.daftar);

        addImage.setOnClickListener(v -> openGallery());

        daftar.setOnClickListener(v -> {
            if (nama.getText().toString().isEmpty() ||email.getText().toString().isEmpty()
                    || nohp.getText().toString().isEmpty() || asalkampus.getText().toString().isEmpty() ) {
                nama.setError("Empty");

                email.setError("Empty");
                nohp.setError("Empty");
                asalkampus.setError("Empty");

                nama.requestFocus();
//                jeniskelamin.requestFocus();
                email.requestFocus();
                nohp.requestFocus();
                asalkampus.requestFocus();
        ;
            } else if (bitmap == null) {
                final Dialog dialog1 = new Dialog(getActivity());
                dialog1.setContentView(R.layout.konfirmasi);
                ImageView sudah1 = dialog1.findViewById(R.id.sudah);
                sudah1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uploadData();
                    }
                });
                ImageView belum1 = dialog1.findViewById(R.id.belum);
                belum1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();

            } else {
                final Dialog dialog2 = new Dialog(getActivity());
                dialog2.setContentView(R.layout.konfirmasi);
                ImageView sudah = dialog2.findViewById(R.id.sudah);
                sudah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uploadImage();
                    }
                });
                ImageView belum = dialog2.findViewById(R.id.belum);
                belum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog2.show();

            }
        });
        return view;
    }

    private void uploadData() {
        reference = reference.child("Pendaftar");
        final String uniqueKey = reference.push().getKey();

        String name = nama.getText().toString();

//        String lakilaki = laki.getText().toString();
//        String perempuan1= perempuan.getText().toString();


//        String jk = jeniskelamin.getText().toString();
        String eml = email.getText().toString();
        String hp = nohp.getText().toString();
        String asalkmps = asalkampus.getText().toString();
        String crs = spinner.getSelectedItem().toString();
//        String crs = course.getText().toString();

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(calForTime.getTime());

        Pendaftaran pendaftaran = new Pendaftaran(name, eml, hp, asalkmps, crs, dowloadUrl, date, time, uniqueKey);

        reference.child(uniqueKey).setValue(pendaftaran).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(getActivity(), "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getActivity(), "Ada sesuatu hal yang Salah", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadImage() {
        pd.setMessage("Uploading...");
        pd.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] finalImg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("Pendaftar").child(finalImg + "jpg");
        final UploadTask uploadTask = filePath.putBytes(finalImg);
        uploadTask.addOnCompleteListener(getActivity(), task -> {
            if (task.isSuccessful()) {
                uploadTask.addOnSuccessListener(taskSnapshot -> filePath.getDownloadUrl().addOnSuccessListener(uri -> {
                    dowloadUrl = String.valueOf(uri);
                    uploadData();
                }));
            } else {
                pd.dismiss();
                Toast.makeText(getActivity(), "Ada sesuatu hal yang salah", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void openGallery() {
        Intent pickimage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickimage, REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQ && resultCode == RESULT_OK) ;
        {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            profile.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        item = spinner.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}