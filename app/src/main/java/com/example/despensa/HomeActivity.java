package com.example.despensa;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.despensa.adapters.ProductListAdapter;
import com.example.despensa.databinding.ActivityMainBinding;
import com.example.despensa.fragments.HomeFragment;
import com.example.despensa.fragments.OptionsFragment;
import com.example.despensa.fragments.RecycleFragment;
import com.example.despensa.managers.UserManager;
import com.example.despensa.objects.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private final int REQUEST_CODE_PRODUCT_REGISTRATION = 1;

    private FloatingActionButton addProductButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        addProductButton = findViewById(R.id.addProductButton);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a atividade de cadastro de novo produto
                Intent intent = new Intent(HomeActivity.this, ProductRegistrationActivity.class);
                startActivity(intent);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.menu_home) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.menu_options) {
            selectedFragment = new OptionsFragment();
        } else if (itemId == R.id.menu_recycle) {
            selectedFragment = new RecycleFragment();
        }
        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }
        return true;
    };
}