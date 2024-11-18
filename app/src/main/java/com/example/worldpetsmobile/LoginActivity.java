package com.example.worldpetsmobile;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginActivity extends AppCompatActivity {

    private TabLayout tabLayoutMain;
    private ViewPager2 viewPagerMain;
    private LoginAdapter loginAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        setUpView();
        setUpLoginView();
        handleOnBackPressed();
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
        builder.setTitle("¿Desea cerrar la aplicación?");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setUpLoginView() {
        this.loginAdapter.addFragment(new PersonFragment(), getString(R.string.personas));
        this.loginAdapter.addFragment(new BusinessFragment(), getString(R.string.empresas));
        viewPagerMain.setAdapter(loginAdapter);

        new TabLayoutMediator(tabLayoutMain, viewPagerMain, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("PERSONAS");
                        break;
                    case 1:
                        tab.setText("EMPRESAS");
                        break;
                    default:
                        break;
                }
            }
        }).attach();
    }

    private void setUpView() {
        this.loginAdapter = new LoginAdapter(getSupportFragmentManager(), getLifecycle());
        this.tabLayoutMain = findViewById(R.id.tab_Main);
        this.viewPagerMain = findViewById(R.id.vp_Main);
    }
}