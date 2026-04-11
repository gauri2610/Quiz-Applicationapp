package com.example.quizapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView nameText, bestScoreText, totalAttemptText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameText = findViewById(R.id.nameText);
        bestScoreText = findViewById(R.id.bestScoreText);
        totalAttemptText = findViewById(R.id.totalAttemptText);

        String name = getSharedPreferences("quiz", MODE_PRIVATE)
                .getString("username", "User");

        String history = getSharedPreferences("quiz", MODE_PRIVATE)
                .getString("history", "");

        String[] scores = history.split(",");

        int bestScore = 0;
        int totalAttempts = 0;

        for (String s : scores) {
            if (!s.isEmpty()) {
                int val = Integer.parseInt(s);
                totalAttempts++;

                if (val > bestScore) {
                    bestScore = val;
                }
            }
        }

        nameText.setText("Name: " + name);
        bestScoreText.setText("Best Score: " + bestScore);
        totalAttemptText.setText("Total Attempts: " + totalAttempts);
    }
}