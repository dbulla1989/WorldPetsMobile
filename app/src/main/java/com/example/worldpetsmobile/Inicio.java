package com.example.worldpetsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inicio extends AppCompatActivity {

    private CardView Medicas, Pagos, Sedes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);

        Medicas = findViewById(R.id.card_citas_medicas);
        Pagos = findViewById(R.id.card_pagos);
        Sedes = findViewById(R.id.card_sedes);

    /*    Medicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, CitasMedicasActivity.class);
                startActivity(intent);

            }
        });
        Pagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, CitasMedicasActivity.class);
                startActivity(intent);
            }
        });
        Sedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, CitasMedicasActivity.class);
                startActivity(intent);
            }
        });*/
    }
}