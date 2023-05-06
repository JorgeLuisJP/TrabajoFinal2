package com.example.trabajofinal2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditProfileAgenteActivity extends AppCompatActivity {


    EditText editNameAgente, editEmailAgente, editPlacaAgente,editUsernameAgente, editPasswordAgente;
    Button saveButtonAgente;
    Button exitButtonAgente;
    String nameUser, emailUser, placaUser, usernameUser, passwordUser;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_agente);


        reference = FirebaseDatabase.getInstance().getReference("agentes");
        editNameAgente = findViewById(R.id.editNameAgente);
        editEmailAgente = findViewById(R.id.editEmailAgente);
        editPlacaAgente = findViewById(R.id.editPlacaAgente);
        editUsernameAgente = findViewById(R.id.editUsernameAgente);
        editPasswordAgente = findViewById(R.id.editPasswordAgente);
        saveButtonAgente = findViewById(R.id.saveButtonAgente);
        exitButtonAgente = findViewById(R.id.exitButtonAgente);
        showData();
        saveButtonAgente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isPasswordChanged() || isPlacaChanged() || isEmailChanged()){
                    Toast.makeText(EditProfileAgenteActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileAgenteActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        exitButtonAgente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitUserData();
            }
        });


    }


    private boolean isNameChanged() {
        if (!nameUser.equals(editNameAgente.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editNameAgente.getText().toString());
            nameUser = editNameAgente.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isEmailChanged() {
        if (!emailUser.equals(editEmailAgente.getText().toString())){
            reference.child(usernameUser).child("email").setValue(editEmailAgente.getText().toString());
            emailUser = editEmailAgente.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isPlacaChanged() {
        if (!placaUser.equals(editPlacaAgente.getText().toString())){
            reference.child(usernameUser).child("placa").setValue(editPlacaAgente.getText().toString());
            placaUser = editPlacaAgente.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isPasswordChanged() {
        if (!passwordUser.equals(editPasswordAgente.getText().toString())){
            reference.child(usernameUser).child("password").setValue(editPasswordAgente.getText().toString());
            passwordUser = editPasswordAgente.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    public void showData(){
        Intent intent = getIntent();
        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        placaUser = intent.getStringExtra("placa");
        usernameUser = intent.getStringExtra("username");
        passwordUser = intent.getStringExtra("password");
        editNameAgente.setText(nameUser);
        editEmailAgente.setText(emailUser);
        editPlacaAgente.setText(placaUser);
        editUsernameAgente.setText(usernameUser);
        editPasswordAgente.setText(passwordUser);
    }

    public void exitUserData(){
        String userUsername = editUsernameAgente.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("agentes");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                    String placaFromDB = snapshot.child(userUsername).child("placa").getValue(String.class);
                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    Intent intent = new Intent(EditProfileAgenteActivity.this, ProfileAgenteActivity.class);
                    intent.putExtra("name", nameFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("placa", placaFromDB);
                    intent.putExtra("username", usernameFromDB);
                    intent.putExtra("password", passwordFromDB);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }


}