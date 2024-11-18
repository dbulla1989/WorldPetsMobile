package com.example.worldpetsmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldpetsmobile.databinding.ActivityMainApplicatonBinding;
import com.example.worldpetsmobile.entities.User;
import com.example.worldpetsmobile.globalresource.GlobalResource;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;
import java.lang.reflect.Field;


public class MainApplicaton extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainApplicatonBinding binding;
    private Integer id;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getIntent().getIntExtra("id", 0);
        binding = ActivityMainApplicatonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMainApplicaton.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        Button updateButton = navigationView.findViewById(R.id.btn_update_customer);
        Button logoutButton = navigationView.findViewById(R.id.btn_logout);
        UpdateData();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainApplicaton.this, UpdateCustomer.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_maps_activity)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_applicaton);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        handleOnBackPressed();
    }

    private void UpdateData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = null;
                try {
                    response = GlobalResource.getInstance().SendRequest("api/person/" + id, "GET", null);

                    if (!response.isEmpty()) {

                        user = new Gson().fromJson(response, User.class);
                        saveUserToSharedPreferences(user);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                View headerView = binding.navView.getHeaderView(0);
                                TextView nameTextView = headerView.findViewById(R.id.tv_userTitle);
                                TextView emailTextView = headerView.findViewById(R.id.tv_emailTitle);

                                if (user.getNombre() != null) {
                                    nameTextView.setText(user.getNombre() + ' ' + user.getApellido());
                                }
                                if (user.getEmail() != null) {
                                    emailTextView.setText(user.getEmail());
                                }
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void saveUserToSharedPreferences(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Field[] fields = user.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                String fieldName = field.getName();
                Object fieldValue = field.get(user);

                if (fieldValue != null) {
                    if (fieldValue instanceof String) {
                        editor.putString(fieldName, (String) fieldValue);
                    } else if (fieldValue instanceof Integer) {
                        editor.putInt(fieldName, (Integer) fieldValue);
                    } else if (fieldValue instanceof Boolean) {
                        editor.putBoolean(fieldName, (Boolean) fieldValue);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        editor.apply();
    }

    private void handleOnBackPressed() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showLogoutDialog();
            }
        });
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Desea cerrar la sesión?");
        builder.setMessage("Al cerrar la sesión, regresará a la pantalla principal.");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainApplicaton.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_applicaton, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_applicaton);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}