package com.example.testiq.account;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class ForgotPassFragment extends Fragment {
    private View v;
    private TextInputEditText txtname, txtpass, txtconfirm;
    private Button btn_forgot;
    private FirebaseDatabase mData;
    private DatabaseReference mRefer;
    private User user;
    private ArrayList<User> userArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_forgot,container,false);
        findviewbyId();
        event();
        return v;
    }
    private void loadAcc(){
        mRefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    user = keyNode.getValue(User.class);
                    userArrayList.add(user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });
    }
    private void event() {
        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadAcc();
                String name = txtname.getText().toString();
                String pass = txtpass.getText().toString();
                String confirm = txtconfirm.getText().toString();

                if(!pass.equals(confirm)){
                    txtconfirm.setText("");
                    txtconfirm.findFocus();
                    Toast.makeText(getContext(), "Nhập lại mật khẩu!", Toast.LENGTH_SHORT).show();
                }
                else {
                    for (int i = 0; i <= userArrayList.size() - 1; i++) {
                        if (name.equals(userArrayList.get(i).getUsername())) {
                            userArrayList.get(i).setPassword(pass.trim());
                            userArrayList.get(i).setConfirmpass(confirm.trim());
                            mRefer.child(String.valueOf(i+1)).setValue(userArrayList.get(i));

                            loadFragment(new LoginFragment());
                            Toast.makeText(getContext(), "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
//                    Toast.makeText(getContext(), "Bạn chưa có tài khoản của Test IQ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findviewbyId() {

        mData = FirebaseDatabase.getInstance();
        mRefer= mData.getReference("Account");

        txtname =  v.findViewById(R.id.edit_forgot_name);
        txtpass = v.findViewById(R.id.edit_forgot_pass);
        txtconfirm = v.findViewById(R.id.edit_forgot_confirmpass);
        btn_forgot = v.findViewById(R.id.btn_forgot);

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
