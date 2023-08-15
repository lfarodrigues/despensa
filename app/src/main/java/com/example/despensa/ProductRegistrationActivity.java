package com.example.despensa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProductRegistrationActivity extends AppCompatActivity {
    private EditText newProductNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_registration);

        newProductNameEditText = findViewById(R.id.newProductNameEditText);
        Button registerProductButton = findViewById(R.id.registerProductButton);

        registerProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newProductName = newProductNameEditText.getText().toString();
                if (!newProductName.isEmpty()) {
                    // Retorna o novo produto para a HomeActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newProduct", newProductName);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}