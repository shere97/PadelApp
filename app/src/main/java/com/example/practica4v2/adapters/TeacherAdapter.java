package com.example.practica4v2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practica4v2.R;
import com.example.practica4v2.model.TeacherModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TeacherAdapter extends FirebaseRecyclerAdapter<TeacherModel, TeacherAdapter.ViewHolder> {


    private OnItemClickListener listener;

    private Context context;

    public interface OnItemClickListener{
        void OnItemClick(TeacherModel teacher);
    }
    public TeacherAdapter(@NonNull FirebaseRecyclerOptions<TeacherModel> options, OnItemClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull TeacherAdapter.ViewHolder viewHolder, int i, @NonNull TeacherModel teacherModel) {

        viewHolder.tv_teacher_name.setText(teacherModel.getName());
        viewHolder.tv_teacher_level.setText(teacherModel.getLevel().toString());
        viewHolder.tv_teacher_location.setText(teacherModel.getLocation());
        viewHolder.tv_teacher_email.setText(teacherModel.getEmail());


        Glide.with(context)
                        .load(teacherModel.getUrl())
                                .into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(v -> {
            if (listener!=null){
                listener.OnItemClick(teacherModel);
            }
        });
    }

    @NonNull
    @Override
    public TeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_teacher_name, tv_teacher_level, tv_teacher_location, tv_teacher_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_teacher_name = itemView.findViewById(R.id.teacher_name);
            tv_teacher_level = itemView.findViewById(R.id.teacher_level);
            tv_teacher_location = itemView.findViewById(R.id.teacher_location);
            tv_teacher_email = itemView.findViewById(R.id.teacher_email);
        }
    }
}
