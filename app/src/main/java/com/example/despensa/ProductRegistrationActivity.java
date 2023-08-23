package com.example.despensa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ProductRegistrationActivity extends AppCompatActivity {
    private EditText newProductNameEditText;
    private EditText purchaseDateEditText;
    private EditText expirationDateEditText;
    private EditText qtdEditText;
    private Spinner spinner;
    private String selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_registration);

        newProductNameEditText = findViewById(R.id.newProductNameEditText);
        purchaseDateEditText = findViewById(R.id.editTextPurchaseDate);
        expirationDateEditText = findViewById(R.id.editTextPurchaseDate);
        qtdEditText = findViewById(R.id.editTextQuantity);
        // Código do spin
        spinner = findViewById(R.id.spinnerCategory);
        // Popule o Spinner com dados usando um ArrayAdapter
        String[] opcoes = {"Orgânico", "Plástico", "Metal", "Vidro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Defina um ouvinte para lidar com a seleção do Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
                // Faça algo com o item selecionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Caso nenhum item seja selecionado
            }
        });

        Button registerProductButton = findViewById(R.id.registerProductButton);


        registerProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newProductName = newProductNameEditText.getText().toString();
                String purchaseDate = purchaseDateEditText.getText().toString();
                String expirationDate = expirationDateEditText.getText().toString();
                String qtd = qtdEditText.getText().toString();

                if (newProductName.isEmpty() || purchaseDate.isEmpty() || expirationDate.isEmpty()
                    || qtd.isEmpty() || selectedItem.isEmpty()) {
                    Toast.makeText(ProductRegistrationActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    // Retorna o novo produto para a HomeActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newProduct", newProductName);
                    resultIntent.putExtra("purchaseDate", purchaseDate);
                    resultIntent.putExtra("expirationDate", expirationDate);
                    resultIntent.putExtra("quantity", qtd);
                    resultIntent.putExtra("category", selectedItem);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}