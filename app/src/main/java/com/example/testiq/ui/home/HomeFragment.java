package com.example.testiq.ui.home;

import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testiq.R;

public class HomeFragment extends Fragment {

    private  View v;
    private TextView t1,t2,t3;
    private SpannableString s1,s2,s3;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);

        t1= v.findViewById(R.id.text1);
        t2= v.findViewById(R.id.text2);
        t3= v.findViewById(R.id.text3);

        s1 = new SpannableString(t1.getText().toString());
        s1.setSpan(new android.text.style.LeadingMarginSpan.Standard(30, 0), 0, 1, 0);
        t1.setText(s1);

        s2= new SpannableString(t2.getText().toString());
        s2.setSpan(new android.text.style.LeadingMarginSpan.Standard(30, 0), 0, 1, 0);
        t2.setText(s2);

        s3 =  new SpannableString(t3.getText().toString());
        s3.setSpan(new android.text.style.LeadingMarginSpan.Standard(30, 0), 0, 1, 0);
        t3.setText(s3);

        return v;
    }
}