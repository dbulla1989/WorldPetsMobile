package com.example.worldpetsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.worldpetsmobile.globalresource.GlobalResource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterUser extends AppCompatActivity {
    private Spinner tipoDocumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_user);


        tipoDocumento=(Spinner) findViewById(R.id.spinnerTipoIdentificacion);
        List<String> options = new ArrayList<>();
        options.add("Cedula de Ciudadania");
        options.add("Cedula de Extranjeria");
        options.add("Pasaporte");
        options.add("Permiso de Permanencia");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, options);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        tipoDocumento.setAdapter(adapter);
    }


    public void Registro (View view) throws JSONException, IOException {

        EditText editTextNumber = findViewById(R.id.editTextNumber);
        EditText editTextName = findViewById(R.id.editTextNames);
        EditText editTextSurename = findViewById(R.id.editTextSurenames);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        String idNumber = editTextNumber.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String surename = editTextSurename.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Verificar que todos los campos estén llenos
        if (idNumber.isEmpty() || name.isEmpty() || surename.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return; // Salir del método si hay campos vacíos
        }

        // Verificar si las contraseñas coinciden
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no son idénticas", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("identificationType", tipoDocumento.getSelectedItem().toString());
        jsonObject.put("identification", idNumber);
        jsonObject.put("names", name);
        jsonObject.put("surnames", surename);
        jsonObject.put("cellPhone", phone);
        jsonObject.put("email", email);

        JSONObject credentials = new JSONObject();
        credentials.put("username", email);
        credentials.put("password", password);

        jsonObject.put("userRequest", credentials);

        String response = GlobalResource.getInstance().SendRequest("api/Person", "POST", jsonObject.toString());
        if (!response.isEmpty()){
            Toast.makeText (this, "El usuario se registro correctamente.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterUser.this , LoginActivity.class);
            startActivity(intent);
        }
    }
}