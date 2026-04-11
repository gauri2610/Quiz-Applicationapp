package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button startQuizBtn, scoreBtn, leaderboardBtn, logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startQuizBtn = findViewById(R.id.startQuizBtn);
        scoreBtn = findViewById(R.id.scoreBtn);
        leaderboardBtn = findViewById(R.id.leaderboardBtn);
        logoutBtn = findViewById(R.id.logoutBtn);

        // Start Quiz
        startQuizBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, CategoryActivity.class));
        });

        // View Score
        scoreBtn.setOnClickListener(v -> {
            String name = getSharedPreferences("quiz", MODE_PRIVATE)
                    .getString("username", "User");

            int score = getSharedPreferences("quiz", MODE_PRIVATE)
                    .getInt("lastScore", 0);

            Intent intent = new Intent(HomeActivity.this, ResultActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("score", score);
            startActivity(intent);
        });

        // Leaderboard
        leaderboardBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, LeaderboardActivity.class));
        });

        Button historyBtn = findViewById(R.id.historyBtn);

        historyBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
        });

        Button profileBtn = findViewById(R.id.profileBtn);

        profileBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
        });
        // Logout
        logoutBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });
    }
}