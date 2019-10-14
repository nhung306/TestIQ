package com.example.testiq.ui.rate_level;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.testiq.R;
import com.example.testiq.ui.exam.ExamFragment;

public class rateFragment extends Fragment {
    private View v;
    private Button btnBaiThi;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_rate_level, container, false);

        btnBaiThi = v.findViewById(R.id.btnBaiThi);
        btnBaiThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ExamFragment());
            }
        });

        return v;
    }
    private void loadFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.replace(R.id.nav_host_fragment, fragment);
            transaction.addToBackStack(backStateName);
            transaction.commit();
        }
    }
}