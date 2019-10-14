package com.example.testiq.ui.exam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testiq.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ExamFragment extends Fragment {

    private View v;

    private ArrayList<Exam> examArrayList = new ArrayList<>();
    private Exam exam;
    private ExamAdapter adapter ;
    private DatabaseReference mRefer;
    private FirebaseDatabase mData;
    private RecyclerView recyclerView;
    private GridView gv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_exam,container,false);


        finviewbyid();
        adapter = new ExamAdapter(getContext(),R.layout.exam,examArrayList);
        gv.setAdapter(adapter);

        return v;
    }

    private void finviewbyid() {
        gv = v.findViewById(R.id.view_exam);
        examArrayList.add(new Exam("Bài test thứ 1",R.drawable.b1,5,1));
        examArrayList.add(new Exam("Bài test thứ 2",R.drawable.b2,5,2));
        examArrayList.add(new Exam("Bài test thứ 3",R.drawable.b3,7,3));
        examArrayList.add(new Exam("Bài test thứ 4",R.drawable.b4,5,4));
        examArrayList.add(new Exam("Bài test thứ 5",R.drawable.b5,5,5));
        examArrayList.add(new Exam("Bài test thứ 6",R.drawable.b6,7,6));
//        examArrayList.add(new Exam("Bài test thứ 7",R.drawable.b7,7,7));
//        examArrayList.add(new Exam("Bài test thứ 8",R.drawable.b8,5,8));


    }


}