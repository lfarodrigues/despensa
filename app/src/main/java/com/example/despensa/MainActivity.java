package com.example.despensa;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.despensa.managers.UserManager;
import com.example.despensa.objects.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_REGISTER_ACTIVITY = 1;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private TextView registerTextView;
    //private Map<String, String> userCredentials;
    private UserManager userManager;
    /*private ActivityResultLauncher<Intent> registerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        String newEmail = data.getStringExtra("email");
                        String newPassword = data.getStringExtra("password");

                        userCredentials.put(newEmail, newPassword);

                        Toast.makeText(MainActivity.this, "Novo usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);

        //userCredentials = new HashMap<>();
        userManager = UserManager.getInstance();
        userManager.mockUsers();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                User user = userManager.searchUserByEmail(email);

                if (user != null) {
                    String storedPassword = user.getPassword();
                    if (storedPassword.equals(password)) {
                        userManager.setLogedUser(user);
                        // Successful login
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        // Redirect to home page
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        //finish();
                    } else {
                        // Incorrect password
                        Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // User not found
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

        });
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_REGISTER_ACTIVITY);
                //registerLauncher.launch(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_REGISTER_ACTIVITY && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String email = data.getStringExtra("email");
            String password = data.getStringExtra("password");

            if(userManager.searchUserByEmail(email) == null) {
                if (name != null && email != null && password != null) {
                    userManager.addUser(new User(name, email, password));
                } else {
                    Toast.makeText(MainActivity.this, "Campo nulo", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}