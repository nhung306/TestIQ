package com.example.testiq.ui.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testiq.R;

import java.util.ArrayList;

public class QuesAdapter extends RecyclerView.Adapter<QuesAdapter.QuesHolder> {
    private Context context;
    private ArrayList<Questions> list;
    private int check = 0;
    private int [] arr= new int[40];
    private int d= 0,i = 0;

    public QuesAdapter(Context context, ArrayList<Questions> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.question,parent,false);
        return new QuesHolder(v);
    }

    @Override
        public void onBindViewHolder(QuesHolder holder, int position) {
        holder.txt_ques.setText("Câu hỏi: "+( position+1 ));
        holder.ed1.setText(list.get(position).getContent());
        holder.r1.setText(list.get(position).getAns1());
        holder.r2.setText(list.get(position).getAns2());
        holder.r3.setText(list.get(position).getAns3());
        holder.r4.setText(list.get(position).getAns4());
        if(position == (list.size()-1))
        {
            holder.btnKq.setVisibility(Button.VISIBLE);
        }
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radio_1:
                        check=1;
                        break;
                    case R.id.radio_2:
                        check=2;
                        break;
                    case R.id.radio_3:
                        check=3;
                        break;
                    case R.id.radio_4:
                        check=4;
                        break;
                    default:
                        break;
                }
                arr[i]=check;
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class QuesHolder extends RecyclerView.ViewHolder{
        private TextView ed1,txt_ques;
        private RadioButton r1,r2,r3,r4;
        private Button btnKq;
        private RadioGroup radioGroup;
        public QuesHolder(View itemView) {
            super(itemView);
            radioGroup = itemView.findViewById(R.id.radio);
            btnKq = itemView.findViewById(R.id.btnKq);
            txt_ques = itemView.findViewById(R.id.tvQues);
            ed1 = itemView.findViewById(R.id.txt_quesContent);
            r1 = itemView.findViewById(R.id.radio_1);
            r2 = itemView.findViewById(R.id.radio_2);
            r3 = itemView.findViewById(R.id.radio_3);
            r4 = itemView.findViewById(R.id.radio_4);
        }
    }
}
