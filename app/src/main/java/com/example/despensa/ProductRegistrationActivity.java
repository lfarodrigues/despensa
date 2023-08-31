package com.example.despensa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProductRegistrationActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText newProductNameEditText;
    private EditText purchaseDateEditText;
    private EditText expirationDateEditText;
    private EditText qtdEditText;
    private Spinner spinner;
    private String selectedItem;
    private SimpleDateFormat dateFormat;
    private TextWatcher purchaseDateTextWatcher;
    private TextWatcher expirationDateTextWatcher;
    private Bitmap imageBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_registration);

        imageBitmap = null;

        newProductNameEditText = findViewById(R.id.newProductNameEditText);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        purchaseDateEditText = findViewById(R.id.editTextPurchaseDate);
        expirationDateEditText = findViewById(R.id.editTextExpirationDate);

        purchaseDateTextWatcher = createTextWatcher(purchaseDateEditText);
        expirationDateTextWatcher = createTextWatcher(expirationDateEditText);

        purchaseDateEditText.addTextChangedListener(purchaseDateTextWatcher);
        expirationDateEditText.addTextChangedListener(expirationDateTextWatcher);

        qtdEditText = findViewById(R.id.editTextQuantity);
        // Código do spin
        spinner = findViewById(R.id.spinnerCategory);
        // Popule o Spinner com dados usando um ArrayAdapter
        String[] options = {"Orgânico", "Plástico", "Metal", "Vidro", "Plástico", "Outro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
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

        FloatingActionButton captureButton = findViewById(R.id.openCameraButton);
        captureButton.setOnClickListener(view -> captureImage());

        Button registerProductButton = findViewById(R.id.registerProductButton);
        registerProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newProductName = newProductNameEditText.getText().toString();
                String purchaseDate = purchaseDateEditText.getText().toString();
                String expirationDate = expirationDateEditText.getText().toString();
                String qtd = qtdEditText.getText().toString();

                byte[] byteArray = null;

                if (imageBitmap != null) {
                    // Converte o bitmap em um byte array
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byteArray = stream.toByteArray();
                }

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
                    resultIntent.putExtra("imageByteArray", byteArray);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            //imageViewProduct.setImageBitmap(imageBitmap);
        }
    }


    private TextWatcher createTextWatcher(final EditText editText){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                formatInputText(editable, editText);
            }
        };
    }
    private void formatInputText(Editable editable, EditText editText) {
        purchaseDateEditText.removeTextChangedListener(purchaseDateTextWatcher);
        expirationDateEditText.removeTextChangedListener(expirationDateTextWatcher);

        String inputText = editable.toString();

        try {
            Date date = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).parse(inputText);
            String formattedDate = dateFormat.format(date);
            editable.replace(0, editable.length(), formattedDate, 0, formattedDate.length());
        } catch (ParseException e) {
            // A data inserida ainda não está completa
        }
        purchaseDateEditText.addTextChangedListener(purchaseDateTextWatcher);
        expirationDateEditText.addTextChangedListener(expirationDateTextWatcher);
    }
}
