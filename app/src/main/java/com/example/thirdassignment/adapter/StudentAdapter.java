package com.example.thirdassignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thirdassignment.R;
import com.example.thirdassignment.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyHolder> {

    List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_details, parent, false);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        final Student student = studentList.get(position);
        String gender = student.getGender();

        holder.tName.setText(student.getName());
        holder.tAge.setText(student.getAge());
        holder.tAddress.setText(student.getAddress());

        holder.tGender.setText(student.getGender());

        if (gender.equals("Male")) {
            holder.imageView.setImageResource(R.drawable.male_icon);
        } else if (gender.equals("Female")) {
            holder.imageView.setImageResource(R.drawable.female_ic);
        } else {
            holder.imageView.setImageResource(R.drawable.image);
        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentList.remove(student);

                notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tName, tAge, tAddress, tGender;
        Button btn;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            tName = itemView.findViewById(R.id.tvName);
            tAge = itemView.findViewById(R.id.tvAge);
            tAddress = itemView.findViewById(R.id.tvAddress);
            tGender = itemView.findViewById(R.id.tvGender);
            btn = itemView.findViewById(R.id.btnDelete);
        }
    }

}
