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
    TextView tituloNombre, tituloUsuario;

    String nombreUser, latitudUser, longitudUser, usuarioUser, pedidoUser;

    DatabaseReference referenceS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud);

        referenceS = FirebaseDatabase.getInstance().getReference("solicitudes");
        detalleNombre = findViewById(R.id.detalleNombre);
        detalleLatitud = findViewById(R.id.detalleLatitud);
        detalleLongitud = findViewById(R.id.detalleLongitud);
        detallePedido = findViewById(R.id.detallePedido);
        detalleUsuario = findViewById(R.id.detalleUsuario);
        tituloNombre = findViewById(R.id.tituloNombre);
        tituloUsuario = findViewById(R.id.tituloUsuario);
        showAllUserData();



    }

    public void showAllUserData(){
        Intent intent = getIntent();
        nombreUser = intent.getStringExtra("nombre");
        latitudUser = intent.getStringExtra("latitud");
        longitudUser = intent.getStringExtra("longitud");
        usuarioUser = intent.getStringExtra("usuario");
        pedidoUser = intent.getStringExtra("pedido");
        tituloNombre.setText(nombreUser);
        tituloUsuario.setText(usuarioUser);
        detalleNombre.setText(nombreUser);
        detalleLatitud.setText(latitudUser);
        detalleLongitud.setText(longitudUser);
        detalleUsuario.setText(usuarioUser);
        detallePedido.setText(pedidoUser);
    }




}