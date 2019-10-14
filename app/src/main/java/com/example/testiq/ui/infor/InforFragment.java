package com.example.testiq.ui.infor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.testiq.MainActivity;
import com.example.testiq.R;
import com.example.testiq.account.LoginFragment;
import com.example.testiq.account.ResgiterFragment;
import com.example.testiq.account.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static androidx.media.MediaBrowserServiceCompat.RESULT_OK;
import static com.example.testiq.account.LoginFragment.MY_PREFS_NAME;

public class InforFragment extends Fragment {
    private View v;
    private TextInputEditText e1,e2,e3;
    private Button btnSelectImage,btnSave;
    private ImageView img;

    private ArrayList <User> users = new ArrayList<>();
    private FirebaseDatabase mData;
    private DatabaseReference mRefer;
    private User user;

    private String name,pass,email;
    private SharedPreferences prefs;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_infor, container, false);

        findviewbyId();
        infor();
        even();

        return v;
    }
    private void findviewbyId() {
        mData = FirebaseDatabase.getInstance();
        mRefer= mData.getReference("Account");

        e1 = v.findViewById(R.id.edit_hoten_infor);
        e2 = v.findViewById(R.id.edit_mk_infor);
        e3 = v.findViewById(R.id.edit_email_infor);
        btnSelectImage = v.findViewById(R.id.btnSelectImage);
        btnSave = v.findViewById(R.id.btn_save);
        img = v.findViewById(R.id.imgInfor);

    }
    private void loadAccount(){
        mRefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    user = keyNode.getValue(User.class);
                    users.add(user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void infor() {
        prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        name = prefs.getString("Name","");
        pass = prefs.getString("Pass","");
        email = prefs.getString("Email","");
        e1.setText(name);
        e2.setText(pass);
        e3.setText(email);
    }

    private void even() {
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    startGallery();
                }
                else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2000);
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loadAccount();
                for(int i= 0; i<= users.size()-1; i++){
                    if(e1.equals(users.get(i).getUsername())){
                        users.get(i).setUsername(e1.getText().toString().trim());
                        users.get(i).setPassword(e2.getText().toString().trim());
                        users.get(i).setEmail(e3.getText().toString().trim());
                        Toast.makeText(getContext(),"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
        }
        Uri returnUri;
        returnUri = data.getData();

        Glide.with(this)
                .load(returnUri)
                .into(img);
    }

}