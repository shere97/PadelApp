package com.example.practica4v2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practica4v2.adapters.BookingsAdapter;
import com.example.practica4v2.model.Reserva;
import com.example.practica4v2.model.TeacherModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class Bookings extends Fragment {

    RecyclerView recyclerView;
    BookingsAdapter bookingsAdapter;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);
        recyclerView = view.findViewById(R.id.my_recyclerView_bookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference reservasRef = FirebaseDatabase.getInstance().getReference().child("reservas").child(userId);



        FirebaseRecyclerOptions<Reserva> options = new FirebaseRecyclerOptions.Builder<Reserva>()
                .setQuery(reservasRef, Reserva.class)
                .build();
        bookingsAdapter = new BookingsAdapter(options);
        recyclerView.setAdapter(bookingsAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        bookingsAdapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        bookingsAdapter.stopListening();
    }
}