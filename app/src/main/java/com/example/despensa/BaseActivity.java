package com.example.despensa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.despensa.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        setupToolbar();
    }

    protected void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar os ícones e os cliques nos ícones
        configureNavigationIcons();
    }

    private void configureNavigationIcons() {
        ImageView leftIcon = findViewById(R.id.leftIcon);
        ImageView middleIcon = findViewById(R.id.middleIcon);
        ImageView rightIcon = findViewById(R.id.rightIcon);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lidar com o clique no ícone da esquerda (menu de opções)
            }
        });

        middleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lidar com o clique no ícone do meio (home)
                Toast.makeText(BaseActivity.this, "Home icon click!", Toast.LENGTH_SHORT).show();
            }
        });

        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lidar com o clique no ícone da direita (outra tela)
            }
        });
    }

}
