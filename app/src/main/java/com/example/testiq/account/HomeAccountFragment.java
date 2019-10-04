package com.example.testiq.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testiq.R;
import com.example.testiq.ui.account.LoginFragment;

public class HomeAccountFragment extends Fragment {
    private  View v;
    private  Button btn_login,btn_register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v= inflater.inflate(R.layout.fragement_account,container,false);
         findviewbyid();
         even();
        return v;
    }

    private void even() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new LoginFragment());

            }

        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ResgiterFragment());
            }
        });
    }

    private void findviewbyid() {
        btn_login= (Button)v.findViewById(R.id.btn_Sign_In);
        btn_register= (Button)v.findViewById(R.id.btn_Sign_Up);
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
