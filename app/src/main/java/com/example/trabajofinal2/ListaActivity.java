package com.example.trabajofinal2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaActivity extends AppCompatActivity {

    Button iniUsuarioButton;
    Button iniAgenteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        iniUsuarioButton = findViewById(R.id.ini_usuario_button);
        iniAgenteButton = findViewById(R.id.ini_agente_button);

        iniUsuarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        iniAgenteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, SignupAgenteActivity.class);
                startActivity(intent);
            }
        });

    }
}