package com.example.quizapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView scoreText, resultMsg;
    Button restartBtn, homeBtn;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreText = findViewById(R.id.scoreText);
        resultMsg = findViewById(R.id.resultMsg);
        restartBtn = findViewById(R.id.restartBtn);
        homeBtn = findViewById(R.id.homeBtn);

        String name = getIntent().getStringExtra("name");
        int score = getIntent().getIntExtra("score", -1);

        TextView nameText = findViewById(R.id.nameText);

        nameText.setText("Player: " + name);

        if (score == -1) {
            scoreText.setText("No Score Available");
            resultMsg.setText("Play Quiz First 😅");
        } else {
            scoreText.setText("Your Score: " + score);

            if (score >= 4) {
                resultMsg.setText("Excellent 🎉");
                mediaPlayer = MediaPlayer.create(this, R.raw.win);
            } else if (score >= 2) {
                resultMsg.setText("Good 👍");
                mediaPlayer = MediaPlayer.create(this, R.raw.win);
            } else {
                resultMsg.setText("Try Again 😅");
                mediaPlayer = MediaPlayer.create(this, R.raw.lose);
            }

            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        }
        // Restart
        restartBtn.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this, CategoryActivity.class));
            finish();
        });

        // Home
        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this, HomeActivity.class));
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
