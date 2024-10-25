package com.example.worldpetsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Iniciar la siguiente actividad
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                // Opcional: Cerrar la actividad actual
                finish();
            }
        }, 4000); // tiempo en milisegundos



    }
}