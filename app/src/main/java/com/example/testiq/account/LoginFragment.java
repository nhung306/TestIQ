package com.example.testiq.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testiq.MainActivity;
import com.example.testiq.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    private View v;
    private Button btn_login;
    private TextView txt_register,txt_forgot;
    private TextInputEditText edit_name,edit_pass;

    private ArrayList <User> users = new ArrayList<>();
    private FirebaseDatabase mData;
    private DatabaseReference mRefer;
    private String username,pass;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private SharedPreferences sharedPreferences;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_login,container,false);
        findviewbyid();
        even();
        return v;
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

    private void even() {
        loadAccount();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edit_name.getText().toString();
                pass = edit_pass.getText().toString();
                 for (int i =0; i<=users.size()-1;i++){
                     if (username.equals(users.get(i).getUsername()) && pass.equals(users.get(i).getPassword())) {
                         Toast.makeText(v.getContext(),"Chào mừng đến với ứng dụng Test IQ",Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(v.getContext(), MainActivity.class);
                         startActivity(intent);

                         sharedPreferences= getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                         SharedPreferences.Editor editor = sharedPreferences.edit();
                         editor.putString("Name",username);
                         editor.putString("Pass", pass);
                         editor.putString("Email",users.get(i).getEmail());
                         editor.putString("Confirm",users.get(i).getConfirmpass());
                         editor.apply();
                     }
                 }
                if (!username.equals(user.getUsername()) && !pass.equals(user.getPassword())) {
                    Toast.makeText(v.getContext(), "Tên hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                }
            }
        });


        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ResgiterFragment());
            }
        });

        txt_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ForgotPassFragment());
            }
        });
    }

    private void findviewbyid() {

        mData = FirebaseDatabase.getInstance();
        mRefer= mData.getReference("Account");

        txt_forgot = (TextView) v.findViewById(R.id.txt_forgot);
        edit_name  =(TextInputEditText) v.findViewById(R.id.edit_login_username);
        edit_pass = (TextInputEditText) v.findViewById(R.id.edit_login_pass);
        btn_login = (Button)v.findViewById(R.id.btn_Sign_In);
        txt_register = (TextView)v.findViewById(R.id.txt_sign_up);
    }
    private void loadFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.replace(R.id.fragAccount, fragment);
            transaction.addToBackStack(backStateName);
            transaction.commit();
        }
    }
}
