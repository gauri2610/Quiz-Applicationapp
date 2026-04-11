package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText name, email, password, confirmPassword;
    Button registerBtn;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        registerBtn = findViewById(R.id.registerBtn);
        loginText = findViewById(R.id.loginText);

        registerBtn.setOnClickListener(v -> {

            String n = name.getText().toString();
            String e = email.getText().toString();
            String p = password.getText().toString();
            String cp = confirmPassword.getText().toString();

            String userName = name.getText().toString();

// 👉 Save Name
            getSharedPreferences("quiz", MODE_PRIVATE)
                    .edit()
                    .putString("username", userName)
                    .apply();

            if (TextUtils.isEmpty(n)) {
                name.setError("Enter Name");
                return;
            }

            if (TextUtils.isEmpty(e)) {
                email.setError("Enter Email");
                return;
            }

            if (TextUtils.isEmpty(p)) {
                password.setError("Enter Password");
                return;
            }

            if (!p.equals(cp)) {
                confirmPassword.setError("Password not match");
                return;
            }

            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        });

        // Back to Login
        loginText.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}