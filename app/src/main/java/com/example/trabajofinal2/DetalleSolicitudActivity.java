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

public class DetalleSolicitudActivity extends AppCompatActivity {

    TextView detalleNombre, detalleLatitud, detalleLongitud, detallePedido, detalleUsuario;
    //TextView regresarSolicitud;
    Button listaSolicitudButton;

    String nombreUser, latitudUser, longitudUser, usuarioUser, pedidoUser;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud);

        reference = FirebaseDatabase.getInstance().getReference("solicitudes");
        detalleNombre = findViewById(R.id.detalleNombre);
        detalleLatitud = findViewById(R.id.detalleLatitud);
        detalleLongitud = findViewById(R.id.detalleLongitud);
        detallePedido = findViewById(R.id.detallePedido);
        detalleUsuario = findViewById(R.id.detalleUsuario);
        listaSolicitudButton = findViewById(R.id.lista_solicitud_button);
        showAllUserData();


        listaSolicitudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleSolicitudActivity.this, ListaSolicitudActivity.class);
                startActivity(intent);
            }
        });

    }

    public void showAllUserData(){
        Intent intent = getIntent();
        nombreUser = intent.getStringExtra("nombre");
        latitudUser = intent.getStringExtra("latitud");
        longitudUser = intent.getStringExtra("longitud");
        usuarioUser = intent.getStringExtra("usuario");
        pedidoUser = intent.getStringExtra("pedido");
        detalleUsuario.setText(usuarioUser);
        detalleNombre.setText(nombreUser);
        detalleLatitud.setText(latitudUser);
        detalleLongitud.setText(longitudUser);
        detallePedido.setText(pedidoUser);
    }




}