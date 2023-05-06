package com.example.trabajofinal2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupAgenteActivity extends AppCompatActivity {


    EditText signupNameAgente, signupUsernameAgente,signupPlacaAgente ,signupEmailAgente, signupPasswordAgente;
    TextView loginAgenteRedirectText;
    Button signupAgenteButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_agente);


        signupNameAgente = findViewById(R.id.signup_agente_name);
        signupEmailAgente = findViewById(R.id.signup_agente_email);
        signupPlacaAgente = findViewById(R.id.signup_agente_placa);
        signupUsernameAgente = findViewById(R.id.signup_agente_username);
        signupPasswordAgente = findViewById(R.id.signup_agente_password);
        loginAgenteRedirectText = findViewById(R.id.loginAgenteRedirectText);
        signupAgenteButton = findViewById(R.id.signup_agente_button);
        signupAgenteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("agentes");
                String name = signupNameAgente.getText().toString();
                String email = signupEmailAgente.getText().toString();
                String placa = signupPlacaAgente.getText().toString();
                String username = signupUsernameAgente.getText().toString();
                String password = signupPasswordAgente.getText().toString();
                HelperClassAgente helperClassAgente = new HelperClassAgente(name, email, placa, username, password);
                reference.child(username).setValue(helperClassAgente);
                Toast.makeText(SignupAgenteActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupAgenteActivity.this, LoginAgenteActivity.class);
                startActivity(intent);
            }
        });
        loginAgenteRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupAgenteActivity.this, LoginAgenteActivity.class);
                startActivity(intent);
            }
        });


    }
}