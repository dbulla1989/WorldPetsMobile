package com.example.worldpetsmobile;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

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

    }

    private void setUpLoginView() {
        this.loginAdapter.addFragment(new PersonFragment(), getString(R.string.personas));
        this.loginAdapter.addFragment(new BusinessFragment(), getString(R.string.empresas));
        viewPagerMain.setAdapter(loginAdapter);

        //tabLayoutMain.setupWithViewPager(viewPagerMain);
    }

    private void setUpView() {
        this.loginAdapter = new LoginAdapter(getSupportFragmentManager(), getLifecycle());
        this.tabLayoutMain = findViewById(R.id.tab_Main);
        this.viewPagerMain = findViewById(R.id.vp_Main);
    }
}