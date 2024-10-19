package com.example.worldpetsmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        String [] opciones = {"Cédula de ciudadanía", "Cédula de extranjería"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, opciones);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        tipoDocumento.setAdapter(adapter);
    }


    public void Registro (View view){

        EditText editTextNumber = findViewById(R.id.editTextNumber);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        String idNumber = editTextNumber.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Verificar que todos los campos estén llenos
        if (idNumber.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return; // Salir del método si hay campos vacíos
        }

        // Verificar si las contraseñas coinciden
        if (!password.equals(confirmPassword)) {

            Toast.makeText(this, "Las contraseñas no son idénticas", Toast.LENGTH_SHORT).show();
        }
    }
}