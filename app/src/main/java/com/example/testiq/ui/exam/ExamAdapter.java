package com.example.testiq.ui.exam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.testiq.ExamDetailActivity;
import com.example.testiq.R;

import java.util.ArrayList;

public class ExamAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Exam> list;

    public ExamAdapter(Context context, int layout, ArrayList<Exam> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        private TextView ed1, time;
        private ImageButton imagebtn;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layout,null);

            holder.imagebtn = convertView.findViewById(R.id.imagebtn);
            holder.time = convertView.findViewById(R.id.txt_time);
            holder.ed1 = convertView.findViewById(R.id.txt_name);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Exam exam = list.get(position);
        holder.imagebtn.setImageResource(exam.getHinh());
        holder.ed1.setText(exam.getContent());
        holder.time.setText(String.valueOf(exam.getTime())+"p");

        holder.imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, ExamDetailActivity.class);
                    intent.putExtra("Bai",list.get(position).getContent());
                    intent.putExtra("Time",list.get(position).getTime());
                    intent.putExtra("Exam_id",list.get(position).getExam_id());
                    context.startActivity(intent);
            }
        });
        return convertView;
    }
}

