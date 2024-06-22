package com.example.practica4v2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica4v2.R;
import com.example.practica4v2.model.Reserva;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class BookingsAdapter extends FirebaseRecyclerAdapter<Reserva, BookingsAdapter.ViewHolder> {

    private Context context;

    public BookingsAdapter(@NonNull FirebaseRecyclerOptions<Reserva> options) {
        super(options);
    }


    @NonNull
    @Override
    public BookingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.booking_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookingsAdapter.ViewHolder viewHolder, int i, @NonNull Reserva reserva) {

        viewHolder.tv_booking_id.setText(String.format("ID: %s", reserva.getIdReserva()));
        viewHolder.tv_teacher_name.setText(reserva.getTeacher_name());
        viewHolder.tv_booking_hour.setText( reserva.getHoraSeleccionada());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_booking_id, tv_teacher_name, tv_booking_hour;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_booking_id = itemView.findViewById(R.id.booking_id);
            tv_teacher_name = itemView.findViewById(R.id.text_view_reserva_nombre);
            tv_booking_hour = itemView.findViewById(R.id.text_view_reserva_hora);
        }
    }
}
