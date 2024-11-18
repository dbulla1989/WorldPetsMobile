package com.example.worldpetsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldpetsmobile.entities.Service;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListResources extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ServiceCardAdapter adapter;
    private List<Service> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_resources); // Cambia a tu layout de Activity

        ArrayList<Service> petList = getIntent().getParcelableArrayListExtra("petList");

        FloatingActionButton fab = findViewById(R.id.fabService);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, AddPet.class);
//                startActivity(intent);
//            }
//        });

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewService);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1)); // Una columna

        // Datos de ejemplo
        //data = new ArrayList<>();
//        data.add(new Service(, "Max", "Labrador", 3));
//        data.add(new Service(R.drawable.pet_image, "Bella", "Golden Retriever", 2));
//        data.add(new Service(R.drawable.pet_image, "Firulais", "Chandis", 20));
//        // Agrega más mascotas según sea necesario

        adapter = new ServiceCardAdapter(petList);
        recyclerView.setAdapter(adapter);
    }
}