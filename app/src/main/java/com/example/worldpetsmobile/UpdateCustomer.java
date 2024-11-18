package com.example.worldpetsmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.worldpetsmobile.entities.User;
import com.example.worldpetsmobile.globalresource.GlobalResource;
import com.google.gson.Gson;

import java.io.IOException;

public class UpdateCustomer extends AppCompatActivity {

    private User user;
    private EditText name, surname, email, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_customer);

        user = getIntent().getParcelableExtra("user");

        EditText identificationType = (EditText) findViewById(R.id.etTipoIdentificacion);
        EditText identification = (EditText) findViewById(R.id.etIdentificacion);
        name = (EditText) findViewById(R.id.etNombre);
        surname = (EditText) findViewById(R.id.etApellido);
        email = (EditText) findViewById(R.id.etCorreo);
        phone = (EditText) findViewById(R.id.etTelefono);

        identificationType.setHint(user.getTipoIdentificacion());
        identification.setHint(user.getIdentificacion());
        name.setHint(user.getNombre());
        surname.setHint(user.getApellido());
        email.setHint(user.getEmail());
        phone.setHint(user.getTelefono());

        Button btnUpdateCustomer = (Button) findViewById(R.id.btnUpdateCustomer);
        Button btnCancelCustomer = (Button) findViewById(R.id.btnCancelCustomer);

        btnUpdateCustomer.setOnClickListener(v -> {
            showLogoutDialog();
        });

        btnCancelCustomer.setOnClickListener(v -> {finish();});
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Está seguro de modificar los datos?");
        builder.setMessage("Al aceptar no podrá reversar los cambios.");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                User updateUser = new User();
                updateUser.setId(user.getId());
                updateUser.setTipoIdentificacion(user.getTipoIdentificacion());
                updateUser.setIdentificacion(user.getIdentificacion());
                updateUser.setNombre(name.getText().toString());
                updateUser.setApellido(surname.getText().toString());
                updateUser.setEmail(email.getText().toString());
                updateUser.setTelefono(phone.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        String jsonBody = gson.toJson(updateUser);
                        try {
                            String response = GlobalResource.getInstance().SendRequest("api/person", "PUT", jsonBody);
                            if (!response.isEmpty()) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UpdateCustomer.this, "Se actualizo los datos.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(UpdateCustomer.this, MainApplicaton.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}