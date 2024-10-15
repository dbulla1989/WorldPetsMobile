package com.example.worldpetsmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterUser extends AppCompatActivity {
    private Spinner tipoDocumento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_user);


        tipoDocumento=(Spinner) findViewById(R.id.spinnerTipoIdentificacion);
        String [] opciones ={"Cédula de ciudadania","cédula de extranjería"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item,opciones);
        tipoDocumento.setAdapter(adapter);
    }


    public void Registro (View view){


    }
}