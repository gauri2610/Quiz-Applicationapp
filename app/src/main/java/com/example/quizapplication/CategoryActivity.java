package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    Button sportsBtn, historyBtn, gkBtn, scienceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        sportsBtn = findViewById(R.id.sportsBtn);
        historyBtn = findViewById(R.id.historyBtn);
        gkBtn = findViewById(R.id.gkBtn);
        scienceBtn = findViewById(R.id.scienceBtn);

        sportsBtn.setOnClickListener(v -> openQuiz("sports"));
        historyBtn.setOnClickListener(v -> openQuiz("history"));
        gkBtn.setOnClickListener(v -> openQuiz("gk"));
        scienceBtn.setOnClickListener(v -> openQuiz("science"));
    }

    private void openQuiz(String category) {
        Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}