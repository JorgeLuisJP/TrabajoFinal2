package com.example.trabajofinal2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginAgenteActivity extends AppCompatActivity {


    EditText loginUsernameAgente, loginPasswordAgente;
    Button loginAgenteButton;
    TextView signupAgenteRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_agente);

        loginUsernameAgente = findViewById(R.id.login_agente_username);
        loginPasswordAgente = findViewById(R.id.login_agente_password);
        loginAgenteButton = findViewById(R.id.login_agente_button);
        signupAgenteRedirectText = findViewById(R.id.signupAgenteRedirectText);
        loginAgenteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {
                } else {
                    checkUser();
                }
            }
        });
        signupAgenteRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAgenteActivity.this, SignupAgenteActivity.class);
                startActivity(intent);
            }
        });

    }



    public Boolean validateUsername() {
        String val = loginUsernameAgente.getText().toString();
        if (val.isEmpty()) {
            loginUsernameAgente.setError("Username cannot be empty");
            return false;
        } else {
            loginUsernameAgente.setError(null);
            return true;
        }
    }
    public Boolean validatePassword(){
        String val = loginPasswordAgente.getText().toString();
        if (val.isEmpty()) {
            loginPasswordAgente.setError("Password cannot be empty");
            return false;
        } else {
            loginPasswordAgente.setError(null);
            return true;
        }
    }
    public void checkUser(){
        String userUsername = loginUsernameAgente.getText().toString().trim();
        String userPassword = loginPasswordAgente.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("agentes");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    loginUsernameAgente.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userPassword)) {
                        loginUsernameAgente.setError(null);
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String placaFromDB = snapshot.child(userUsername).child("placa").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                        Intent intent = new Intent(LoginAgenteActivity.this, ProfileAgenteActivity.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("placa", placaFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
                        loginPasswordAgente.setError("Invalid Credentials");
                        loginPasswordAgente.requestFocus();
                    }
                } else {
                    loginUsernameAgente.setError("User does not exist");
                    loginUsernameAgente.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}