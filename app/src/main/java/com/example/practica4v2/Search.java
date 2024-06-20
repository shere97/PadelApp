package com.example.practica4v2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practica4v2.adapters.TeacherAdapter;
import com.example.practica4v2.model.TeacherModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Search extends Fragment {

    TeacherAdapter teacherAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.my_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);




        FirebaseRecyclerOptions<TeacherModel> options = new FirebaseRecyclerOptions.Builder<TeacherModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("teacher"), TeacherModel.class)
                .build();

        teacherAdapter = new TeacherAdapter(options);
        recyclerView.setAdapter(teacherAdapter);



        return view;

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