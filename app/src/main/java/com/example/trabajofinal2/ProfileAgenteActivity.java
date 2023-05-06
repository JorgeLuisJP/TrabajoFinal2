package com.example.trabajofinal2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileAgenteActivity extends AppCompatActivity {


    TextView profileNameAgente, profileEmailAgente, profilePlacaAgente, profileUsernameAgente, profilePasswordAgente;
    TextView titleNameAgente, titleUsernameAgente;
    Button editProfileAgente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_agente);


        profileNameAgente = findViewById(R.id.profileNameAgente);
        profileEmailAgente = findViewById(R.id.profileEmailAgente);
        profilePlacaAgente = findViewById(R.id.profilePlacaAgente);
        profileUsernameAgente = findViewById(R.id.profileUsernameAgente);
        profilePasswordAgente = findViewById(R.id.profilePasswordAgente);
        titleNameAgente = findViewById(R.id.titleNameAgente);
        titleUsernameAgente = findViewById(R.id.titleUsernameAgente);
        editProfileAgente = findViewById(R.id.editButtonAgente);
        showAllUserData();
        editProfileAgente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });
    }


    public void showAllUserData(){
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("name");
        String emailUser = intent.getStringExtra("email");
        String placaUser = intent.getStringExtra("placa");
        String usernameUser = intent.getStringExtra("username");
        String passwordUser = intent.getStringExtra("password");
        titleNameAgente.setText(nameUser);
        titleUsernameAgente.setText(usernameUser);
        profileNameAgente.setText(nameUser);
        profileEmailAgente.setText(emailUser);
        profilePlacaAgente.setText(placaUser);
        profileUsernameAgente.setText(usernameUser);
        profilePasswordAgente.setText(passwordUser);
    }
    public void passUserData(){
        String userUsername = profileUsernameAgente.getText().toString().trim();
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
                    Intent intent = new Intent(ProfileAgenteActivity.this, EditProfileAgenteActivity.class);
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