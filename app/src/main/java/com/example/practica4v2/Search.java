package com.example.practica4v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.practica4v2.adapters.TeacherAdapter;
import com.example.practica4v2.model.TeacherModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Search extends Fragment {

    TeacherAdapter teacherAdapter;
    RecyclerView recyclerView;



    TextView textView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.my_recyclerView);
        textView = view.findViewById(R.id.txt_search);

        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);


        textView.setText(create_initial_text(sharedPreferences));


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<TeacherModel> options = new FirebaseRecyclerOptions.Builder<TeacherModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("teacher"), TeacherModel.class)
                .build();

        teacherAdapter = new TeacherAdapter(options, new TeacherAdapter.OnItemClickListener() {

            @Override
            public void OnItemClick(TeacherModel teacher) {
                Intent intent = new Intent(getActivity(), TeacherDetailActivity.class);
                intent.putExtra("teacher_name", teacher.getName());
                intent.putExtra("teacher_level", teacher.getLevel().toString());
                intent.putExtra("teacher_location", teacher.getLocation());
                intent.putExtra("teacher_email", teacher.getEmail());
                intent.putExtra("teacher_id", teacher.getId());
                intent.putExtra("teacher_url", teacher.getUrl());
                intent.putExtra("teacher_description", teacher.getDescription());
                intent.putExtra("teacher_phone", teacher.getPhone());

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(teacherAdapter);



        return view;

    }

    private String create_initial_text(SharedPreferences sharedPreferences) {

        String defaultText = "elije a tu próximo profesor de padel";

        String username = sharedPreferences.getString("name", "Usuario no encontrado");

        return username +"," + defaultText;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        teacherAdapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {
        teacherAdapter.startListening();
        super.onStop();
    }
}