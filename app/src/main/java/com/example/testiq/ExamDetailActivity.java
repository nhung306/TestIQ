package com.example.testiq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testiq.ui.exam.QuesAdapter;
import com.example.testiq.ui.exam.Questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExamDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvPhut, tvGiay,txttest;
    private String baiThi;
    private int time,exam_id;
    private int phut, giay = 0;
    private QuesAdapter adapter;
    private ArrayList<Questions> questionsArrayList = new ArrayList<>();
    private FirebaseDatabase mData;
    private DatabaseReference mRefer;
    private Questions q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_detail);

        findviewById();
        getSession();
        txttest.setText(baiThi);
        mData = FirebaseDatabase.getInstance();
        mRefer = mData.getReference("Question");
        recyclerView.setLayoutManager(new LinearLayoutManager(ExamDetailActivity.this));

        loadQues();
        countTime();
    }

    private void loadQues() {
        mRefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    q = keyNode.getValue(Questions.class);
                    if(exam_id == q.getExam_id()) {
                        questionsArrayList.add(q);
                    }
                }
                adapter = new QuesAdapter(ExamDetailActivity.this, questionsArrayList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }
    private void findviewById() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        tvGiay= (TextView)findViewById(R.id.txtsoGiaylambai);
        tvPhut=(TextView)findViewById(R.id.txtsophutlambai);
        txttest = findViewById(R.id.test);
    }
    private void getSession() {
        Intent intent = getIntent();
        baiThi = intent.getStringExtra("Bai");
        exam_id = intent.getIntExtra("Exam_id",0);
        time = intent.getIntExtra("Time",0);
    }
    private void countTime() {
        phut = time;
        CountDownTimer countDownTimer= new CountDownTimer((time*60*1000),1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvPhut.setText(phut +"");
                tvGiay.setText(giay +"");
                giay--;
                if(giay<=0){
                    giay=59;
                    phut =phut-1;
                }
            }
            @Override
            public void onFinish() {
            }
        };
        countDownTimer.start();
        if(giay == 0 && phut == 0 ){
            Toast.makeText(ExamDetailActivity.this,"Hết thời gian làm bài !!!",Toast.LENGTH_LONG).show();
        }
    }
}