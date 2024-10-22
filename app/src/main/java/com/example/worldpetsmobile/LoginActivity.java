package com.example.worldpetsmobile;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
        prueba();

    }

    private void prueba() {
        int num = 0;
        int num1 = 5;
        int resul = num + num1;
    }

//this.loginAdapter.addFragment(new PersonFragment(), getString(R.string.personas));

    private void setUpLoginView() {
        this.loginAdapter.addFragment(new MapsFragment(), getString(R.string.personas));
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

        //tabLayoutMain.setupWithViewPager(viewPagerMain);
    }



    private void setUpView() {
        this.loginAdapter = new LoginAdapter(getSupportFragmentManager(), getLifecycle());
        this.tabLayoutMain = findViewById(R.id.tab_Main);
        this.viewPagerMain = findViewById(R.id.vp_Main);
    }
}