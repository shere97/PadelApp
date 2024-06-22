package com.example.practica4v2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserOptionsBottomSheet extends BottomSheetDialogFragment {

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
        mAuth = FirebaseAuth.getInstance();

        TextView tvEditUser = view.findViewById(R.id.tv_edit_user);
        TextView tvLogOut = view.findViewById(R.id.tv_logout);
        TextView tvDeleteAccount = view.findViewById(R.id.tv_delete_account);

        tvEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Intent to navegate to edit user screen


            }
        });

        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                dismiss();

            }
        });


        tvDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDeleteConfirmationFragment();
                dismiss();

            }
        });


        return view;
    }

    private void showDeleteConfirmationFragment() {

        new AlertDialog.Builder(getActivity())
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete this account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAccount();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteAccount() {

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            mAuth.signOut(); // Sign out the user before deleting the account
            user.delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Clear any cached data related to the user
                            // Navigate to login screen
                            Intent intent = new Intent(getActivity(), Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    });
        }


    }


}
