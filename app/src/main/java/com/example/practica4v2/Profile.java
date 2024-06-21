package com.example.practica4v2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;


public class Profile extends Fragment {

    //FIREBASE

    private FirebaseAuth firebaseAuth;
    private  FirebaseUser firebaseUser;
    private StorageReference storageReference;

    //UI ELEMENTS

    Button btn_upload, btn_settings;
    ShapeableImageView user_profile_photo;
    TextView current_user_name;

    // VARIABLES
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 1;

    private Uri imageUri;
    private static final String OPENWEATHERMAP_API_URL = "https://api.openweathermap.org/data/2.5/weather";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Firebase Utils
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

        //UI inserts

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btn_upload =  view.findViewById(R.id.btn_upload);
        current_user_name = view.findViewById(R.id.current_user_name);
        user_profile_photo =  view.findViewById(R.id.profile_photo);
        current_user_name.setText(Objects.requireNonNull(firebaseUser).getDisplayName());
        imageUri = firebaseUser.getPhotoUrl();

        requestStoragePermission();
        loadProfileImage();

        view.findViewById(R.id.btn_upload).setOnClickListener(v -> openFileChooser());

        return view;
    }

    private void requestStoragePermission() {

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            loadImageFromUri(imageUri);
            // Actualizar la URL de la foto en Firebase Authentication (opcional)
            updateFirebaseUserProfile(imageUri);
        }
    }

    private void loadImageFromUri(Uri imageUri) {
        Glide.with(this)
                .load(imageUri)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.baseline_person_24) // Imagen predeterminada mientras carga
                        .error(R.drawable.baseline_newspaper_24)      // Imagen a mostrar en caso de error
                        .diskCacheStrategy(DiskCacheStrategy.ALL))    // Cache completo
                .into(user_profile_photo);
    }

    private void loadProfileImage() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Uri photoUrl = currentUser.getPhotoUrl();

            if (photoUrl != null) {
                Glide.with(this)
                        .load(photoUrl)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.baseline_person_24)
                                .error(R.drawable.baseline_newspaper_24)
                                .diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(user_profile_photo);
            } else {
                user_profile_photo.setImageResource(R.drawable.baseline_search_24);
            }
        } else {
            user_profile_photo.setImageResource(R.drawable.baseline_search_24);
        }
    }

    private void updateFirebaseUserProfile(Uri photoUri) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(user.getDisplayName())
                    .setPhotoUri(photoUri)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(task -> {
                    });
        }
    }
}