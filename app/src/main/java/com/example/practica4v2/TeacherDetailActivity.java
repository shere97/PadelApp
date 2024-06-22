package com.example.practica4v2;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TeacherDetailActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);
        Context context = this;

        String name = getIntent().getStringExtra("teacher_name");
        String level = getIntent().getStringExtra("teacher_level");
        String location = getIntent().getStringExtra("teacher_location");
        String email = getIntent().getStringExtra("teacher_email");
        String image = getIntent().getStringExtra("teacher_url");
        String description = getIntent().getStringExtra("teacher_description");
        Long phone = getIntent().getLongExtra("teacher_phone" , 620334191);

        // Asigna los datos a las vistas
        TextView tvName = findViewById(R.id.current_user_name);
        TextView tvEmail = findViewById(R.id.teacher_email);
        TextView tvLevel = findViewById(R.id.teacher_level);
        TextView tvLocation = findViewById(R.id.teacher_location);
        TextView tvDescription = findViewById(R.id.teacher_description);
        TextView tvPhone = findViewById(R.id.teacher_phone);
        ShapeableImageView shapeableImageView = findViewById(R.id.profile_photo);

        Button btnReserve = findViewById(R.id.btn_makeBooking);


        tvName.setText(name);
        tvLevel.setText("Nivel: " + level);
        tvLocation.setText("Ubicación: " + location);
        tvEmail.setText(email);
        tvDescription.setText(description);
        tvPhone.setText(phone.toString());

        Glide.with(context)
                        .load(image)
                                .into(shapeableImageView);


        btnReserve.setOnClickListener(v -> {
            mostrarDialogoConfirmacion();
        });

    }

    private void mostrarDialogoConfirmacion() {

        Calendar calendario = Calendar.getInstance();
        int horaActual = calendario.get(Calendar.HOUR_OF_DAY);
        int minutoActual = calendario.get(Calendar.MINUTE);

        TimePicker timePicker = new TimePicker(this);
        timePicker.setIs24HourView(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona la hora de la reserva");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(timePicker);

        builder.setView(layout);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hourOfDay = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Aquí se ejecuta cuando se selecciona la hora y se hace clic en OK
                confirmarReserva(); // Llamar a método para confirmar reserva
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void confirmarReserva() {

    }

        //TODO : Hacer la conexion con la base de datos
}
