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
    Button solicitarButton, detalleSolicitudButton;
    FirebaseDatabase databaseS;
    DatabaseReference referenceS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);

        solicitudUsuario = findViewById(R.id.solicitud_usuario);
        solicitudNombre = findViewById(R.id.solicitud_nombre);
        solicitudLatitud = findViewById(R.id.solicitud_latitud);
        solicitudLongitud = findViewById(R.id.solicitud_longitud);
        solicitudPedido = findViewById(R.id.solicitud_pedido);
        solicitarButton = findViewById(R.id.solicitar_button);
        detalleSolicitudButton = findViewById(R.id.detalle_solicitud_button);

        solicitarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseS = FirebaseDatabase.getInstance();
                referenceS = databaseS.getReference("solicitudes");
                String nombre = solicitudNombre.getText().toString();
                String latitud = solicitudLatitud.getText().toString();
                String longitud = solicitudLongitud.getText().toString();
                String usuario = solicitudUsuario.getText().toString();
                String pedido = solicitudPedido.getText().toString();
                SolicitudClass solicitudClass = new SolicitudClass(nombre, latitud, longitud,usuario, pedido);
                referenceS.child(usuario).setValue(solicitudClass);
                Toast.makeText(SolicitudActivity.this, "Solicitud completado!", Toast.LENGTH_SHORT).show();
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
                    String usernameFromDB = snapshot.child(userUsername).child("usuario").getValue(String.class);
                    String pedidoFromDB = snapshot.child(userUsername).child("pedido").getValue(String.class);
                    Intent intent = new Intent(SolicitudActivity.this, DetalleSolicitudActivity.class);
                    intent.putExtra("nombre", nombreFromDB);
                    intent.putExtra("latitud", latitudFromDB);
                    intent.putExtra("longitud", longitudFromDB);
                    intent.putExtra("usuario", usernameFromDB);
                    intent.putExtra("pedido", pedidoFromDB);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}