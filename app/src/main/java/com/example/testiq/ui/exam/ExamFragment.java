package com.example.testiq.ui.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testiq.R;
import com.google.android.gms.tasks.OnFailureListener;

import java.util.ArrayList;

public class ExamFragment extends Fragment {

    private View v;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_exam,container,false);


        return v;
    }
}