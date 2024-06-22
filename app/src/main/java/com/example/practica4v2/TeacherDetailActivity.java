package com.example.practica4v2;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.practica4v2.model.Reserva;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Objects;

public class TeacherDetailActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

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
        TextView turnBack = findViewById(R.id.turn_back);
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
            mostrarDialogoConfirmacion(name, location);
        });

        turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void mostrarDialogoConfirmacion(String teacherName, String location) {

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
                confirmarReserva(teacherName, hourOfDay, minute , location); // Llamar a método para confirmar reserva
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

    private void confirmarReserva(String teacher_name, int hourOfDay, int minute, String location) {

        String horaSeleccionada = String.format("%02d:%02d", hourOfDay, minute);
        String userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid(); // Obtenemos el ID del usuario actual
        System.out.println("El USUARIO ES:" + firebaseAuth.getCurrentUser().getUid());
        DatabaseReference reservasRef = databaseRef.child("reservas").child(userId).push();

        String reservaId = reservasRef.getKey();

        Reserva reserva = new Reserva(reservaId, teacher_name, firebaseAuth.getCurrentUser().getEmail(), horaSeleccionada, location);

        reservasRef.setValue(reserva)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(TeacherDetailActivity.this, "Reserva confirmada para " + horaSeleccionada, Toast.LENGTH_SHORT).show();
                        // Aquí puedes navegar a otra actividad o hacer cualquier acción adicional
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TeacherDetailActivity.this, "Error al confirmar la reserva", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
