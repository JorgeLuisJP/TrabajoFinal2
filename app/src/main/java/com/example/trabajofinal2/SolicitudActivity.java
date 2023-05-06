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

public class SolicitudActivity extends AppCompatActivity {

    EditText solicitudNombre, solicitudUsuario, solicitudLatitud, solicitudLongitud, solicitudPedido;
    //TextView detalleRedirectText;
    Button solicitarButton, detalleSolicitudButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);

        solicitudUsuario = findViewById(R.id.solicitud_usuario);
        solicitudNombre = findViewById(R.id.solicitud_nombre);
        solicitudLatitud = findViewById(R.id.solicitud_latitud);
        solicitudLongitud = findViewById(R.id.solicitud_longitud);
        solicitudPedido = findViewById(R.id.solicitud_pedido);
        //detalleRedirectText = findViewById(R.id.detalleRedirectText);
        solicitarButton = findViewById(R.id.solicitar_button);
        detalleSolicitudButton = findViewById(R.id.detalle_solicitud_button);

        solicitarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("solicitudes");
                String usuario = solicitudUsuario.getText().toString();
                String nombre = solicitudNombre.getText().toString();
                String latitud = solicitudLatitud.getText().toString();
                String longitud = solicitudLongitud.getText().toString();
                String pedido = solicitudPedido.getText().toString();
                SolicitudClass solicitudClass = new SolicitudClass(usuario, nombre, latitud, longitud, pedido);
                reference.child(usuario).setValue(solicitudClass);
                Toast.makeText(SolicitudActivity.this, "Solicitud successfully!", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(SolicitudActivity.this, DetalleSolicitudActivity.class);
                //startActivity(intent);
            }
        });

        detalleSolicitudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detalleSolicitudData();
            }
        });



    }


    public void detalleSolicitudData(){
        String userUsername = solicitudUsuario.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("solicitudes");
        Query checkUserDatabase = reference.orderByChild("usuario").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String nombreFromDB = snapshot.child(userUsername).child("nombre").getValue(String.class);
                    String latitudFromDB = snapshot.child(userUsername).child("latitud").getValue(String.class);
                    String longitudFromDB = snapshot.child(userUsername).child("longitud").getValue(String.class);
                    String pedidoFromDB = snapshot.child(userUsername).child("pedido").getValue(String.class);
                    String usernameFromDB = snapshot.child(userUsername).child("usaurio").getValue(String.class);
                    Intent intent = new Intent(SolicitudActivity.this, DetalleSolicitudActivity.class);
                    intent.putExtra("name", nombreFromDB);
                    intent.putExtra("email", latitudFromDB);
                    intent.putExtra("longitud", longitudFromDB);
                    intent.putExtra("pedido", pedidoFromDB);
                    intent.putExtra("username", usernameFromDB);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}