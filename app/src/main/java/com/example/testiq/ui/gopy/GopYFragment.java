package com.example.testiq.ui.gopy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testiq.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GopYFragment extends Fragment {
    private View v;
    private EditText editText;
    private Button btnGopY;
    private FirebaseDatabase mData;
    private DatabaseReference mRefer;
    private GopY opinion;
    private String text;
    long id= 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_gop_y, container,false);

        btnGopY = v.findViewById(R.id.btnGopY);
        editText = v.findViewById(R.id.mtTVGopY);
        opinion = new GopY();
        mData = FirebaseDatabase.getInstance();
        mRefer= mData.getReference("Opinion");

        mRefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    id=(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnGopY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = editText.getText().toString();
                if(text.isEmpty()){
                    Toast.makeText(getContext(),"Nhập nội dung góp ý",Toast.LENGTH_SHORT).show();
                }
                else {
                    saveOpinion();
                    editText.setText("");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setTitle("Thông báo");
                    builder1.setMessage("Cảm ơn bạn đã đóng góp ý kiến này cho chúng tôi.");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder1.show();
                }

            }
        });
        return v;
    }
    private void  saveOpinion(){
        opinion.setContent_opinion(text.trim());
        mRefer.child(String.valueOf(id+1)).setValue(opinion);
    }
}
