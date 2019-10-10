package com.example.testiq.account;

import android.accounts.Account;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testiq.R;
import com.example.testiq.account.LoginFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class ResgiterFragment extends Fragment {
    private View v;
    private TextInputEditText txt_name,txt_pass,txt_email,txt_confirm;
    private Button btn_register;
    private TextView txt_login;
    private String name,email,pass,confirm;
    private User user;
    private DatabaseReference mDatabase;
    private long id = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_register,container,false);
        findviewbyid();
        even();
        return v;
    }

    private void even() {
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name= txt_name.getText().toString();
                email= txt_email.getText().toString();
                pass= txt_pass.getText().toString();
                confirm= txt_confirm.getText().toString();

                if(TextUtils.isEmpty(name)){
                    txt_name.findFocus();
                    Toast.makeText(getContext(), "Nhập họ tên!", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(email)) {
                    txt_email.findFocus();
                    Toast.makeText(getContext(), "Nhập địa chỉ email!", Toast.LENGTH_SHORT).show();
                    }
                else if (TextUtils.isEmpty(pass)) {
                    txt_pass.findFocus();
                    Toast.makeText(getContext(), "Nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                    }
                else if(TextUtils.isEmpty(confirm)){
                    txt_confirm.findFocus();
                    Toast.makeText(getContext(), "Nhập lại mật khẩu!", Toast.LENGTH_SHORT).show();
                }

                else if(!confirm.equals(pass)){
                    txt_confirm.setText("");
                    txt_confirm.findFocus();
                    Toast.makeText(getContext(),"Nhập lại mật khẩu!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Save();

                    Toast.makeText(getContext(),"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
                    loadFragment(new LoginFragment());
                }
            }
        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new LoginFragment());
            }
        });
    }

    private void Save() {

        user.setUsername(name.trim());
        user.setEmail(email.trim());
        user.setPassword(pass.trim());
        user.setConfirmpass(confirm.trim());

        mDatabase.child(String.valueOf(id+1)).setValue(user);
    }

    private void findviewbyid() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Account");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    id=(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        user = new User();

        btn_register = (Button)v.findViewById (R.id.btn_Sign_Up);
        txt_login =(TextView)v.findViewById(R.id.txt_sign_in);

        txt_name = (TextInputEditText)v.findViewById(R.id.edit_register_username);
        txt_email = (TextInputEditText)v.findViewById(R.id.edit_register_email);
        txt_pass = (TextInputEditText)v.findViewById(R.id.edit_register_password);
        txt_confirm = (TextInputEditText)v.findViewById(R.id.edit_register_confirm);


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
