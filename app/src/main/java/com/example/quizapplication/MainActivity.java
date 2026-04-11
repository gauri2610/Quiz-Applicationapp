package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 👉 LoginActivity open
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

        // Fix for "Activity client record must not be null" crash
        // This occurs on some Android versions when finish() is called immediately in onCreate()
        new Handler(Looper.getMainLooper()).post(this::finish);
    }
}