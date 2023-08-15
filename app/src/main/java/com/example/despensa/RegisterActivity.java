package com.example.despensa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.despensa.utils.ValidationUtils;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;
    private EditText newNameEditText;
    private EditText newEmailEditText;

    private EditText newPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newNameEditText = findViewById(R.id.newNameEditText);
        newEmailEditText = findViewById(R.id.newEmailEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = newNameEditText.getText().toString();
                String newEmail = newEmailEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();

                if (ValidationUtils.isValidEmail(newEmail) && ValidationUtils.isValidPassword(newPassword)) {
                    //String result = "a";

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("name", newName);
                    resultIntent.putExtra("email", newEmail);
                    resultIntent.putExtra("password", newPassword);
                    setResult(RESULT_OK, resultIntent);

                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Usuário ou senha em formato inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ...
    }

    // ...
}