package com.example.worldpetsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

public class LoginBusiness extends AppCompatActivity {

    private EditText editTextNIT, editTextCompanyName, editTextAddress, editTextWebsite;
    private EditText editTextPassword, editTextConfirmPassword;
    private CheckBox checkboxVeterinaria, checkboxGrooming, checkboxAlimentacion, checkboxEntrenamiento, checkboxGuarderia, checkboxServiciosEmergencia;
    private Button buttonRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_empresa);


    /*   spinner = findViewById(R.id.spinner);
//        text = findViewById(R.id.txt_email);
//        text1 = findViewById(R.id.txt_password);
//        button = findViewById(R.id.btn_ingresar);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.spinner_items, android.R.layout.simple_spinner_item);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginBusiness.this, LoginActivity.class);
//                startActivity(intent);
//            }
      });*/

        editTextNIT = findViewById(R.id.editTextNIT);
        editTextCompanyName = findViewById(R.id.editTextCompanyName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextWebsite = findViewById(R.id.editTextWebsite);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        checkboxVeterinaria = findViewById(R.id.checkboxVeterinaria);
        checkboxGrooming = findViewById(R.id.checkboxGrooming);
        checkboxAlimentacion = findViewById(R.id.checkboxAlimentacion);
        checkboxEntrenamiento = findViewById(R.id.checkboxEntrenamiento);
        checkboxGuarderia = findViewById(R.id.checkboxGuarderia);
        checkboxServiciosEmergencia = findViewById(R.id.checkboxServiciosEmergencia);
        buttonRegister = findViewById(R.id.buttonRegisterCompany);

        // Configuración del Spinner
        Spinner spinnerContribuyente = findViewById(R.id.spinnerContribuyente);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_contribuyentes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContribuyente.setAdapter(adapter);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarEmpresa();
            }
        });
    }


    private void registrarEmpresa() {
        // Captura los valores ingresados
        String nit = editTextNIT.getText().toString().trim();
        String companyName = editTextCompanyName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String website = editTextWebsite.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Captura el tipo de contribuyente
        Spinner spinnerContribuyente = findViewById(R.id.spinnerContribuyente);
        String tipoContribuyente = spinnerContribuyente.getSelectedItem().toString();

        // Validar que los campos obligatorios no estén vacíos
        if (TextUtils.isEmpty(nit) || TextUtils.isEmpty(companyName) || TextUtils.isEmpty(address) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Por favor, completa todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar que las contraseñas coinciden
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar que al menos un servicio esté seleccionado
        if (!checkboxVeterinaria.isChecked() && !checkboxGrooming.isChecked() && !checkboxAlimentacion.isChecked() &&
                !checkboxEntrenamiento.isChecked() && !checkboxGuarderia.isChecked() && !checkboxServiciosEmergencia.isChecked()) {
            Toast.makeText(this, "Por favor, selecciona al menos un servicio", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí puedes usar tipoContribuyente para procesar la información

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    private void limpiarCampos() {
        editTextNIT.setText("");
        editTextCompanyName.setText("");
        editTextAddress.setText("");
        editTextWebsite.setText("");
        editTextPassword.setText("");
        editTextConfirmPassword.setText("");

        checkboxVeterinaria.setChecked(false);
        checkboxGrooming.setChecked(false);
        checkboxAlimentacion.setChecked(false);
        checkboxEntrenamiento.setChecked(false);
        checkboxGuarderia.setChecked(false);
        checkboxServiciosEmergencia.setChecked(false);
    }

    }

