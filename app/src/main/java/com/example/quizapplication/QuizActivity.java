package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    TextView question, timerText;
    Button option1, option2, option3, option4;

    ArrayList<QuestionModel> questionList;
    int index = 0;
    int score = 0;
    CountDownTimer timer;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question = findViewById(R.id.question);
        timerText = findViewById(R.id.timerText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        progressBar = findViewById(R.id.progressBar);

        questionList = new ArrayList<>();

        String category = getIntent().getStringExtra("category");

        // 👉 Category wise questions (minimum 5)
        if (category != null) {

            if (category.equals("sports")) {
                questionList.add(new QuestionModel("Who won FIFA 2022?", "Brazil", "France", "Argentina", "Germany", "Argentina"));
                questionList.add(new QuestionModel("Wimbledon is for?", "Cricket", "Tennis", "Football", "Hockey", "Tennis"));
                questionList.add(new QuestionModel("Players in cricket team?", "10", "11", "12", "9", "11"));
                questionList.add(new QuestionModel("Most FIFA wins?", "Germany", "Italy", "Brazil", "Argentina", "Brazil"));
                questionList.add(new QuestionModel("Lightning Bolt?", "Usain Bolt", "Lewis", "Gay", "Blake", "Usain Bolt"));
            }

            else if (category.equals("history")) {
                questionList.add(new QuestionModel("First President USA?", "Washington", "Lincoln", "Jefferson", "Adams", "Washington"));
                questionList.add(new QuestionModel("WW2 ended?", "1940", "1945", "1950", "1939", "1945"));
                questionList.add(new QuestionModel("Taj Mahal built by?", "Akbar", "Shah Jahan", "Babur", "Aurangzeb", "Shah Jahan"));
                questionList.add(new QuestionModel("India independence?", "1945", "1947", "1950", "1942", "1947"));
                questionList.add(new QuestionModel("Iron Man India?", "Patel", "Nehru", "Gandhi", "Subhash", "Patel"));
            }

            else if (category.equals("gk")) {
                questionList.add(new QuestionModel("Capital of France?", "Berlin", "Madrid", "Paris", "Rome", "Paris"));
                questionList.add(new QuestionModel("Red Planet?", "Venus", "Mars", "Jupiter", "Saturn", "Mars"));
                questionList.add(new QuestionModel("National animal India?", "Tiger", "Lion", "Elephant", "Dog", "Tiger"));
                questionList.add(new QuestionModel("Currency India?", "Dollar", "Rupee", "Euro", "Yen", "Rupee"));
                questionList.add(new QuestionModel("Largest ocean?", "Indian", "Atlantic", "Pacific", "Arctic", "Pacific"));
            }

            else if (category.equals("science")) {
                questionList.add(new QuestionModel("Water formula?", "O2", "H2O", "CO2", "NaCl", "H2O"));
                questionList.add(new QuestionModel("Sun is?", "Planet", "Star", "Moon", "Asteroid", "Star"));
                questionList.add(new QuestionModel("Bones in body?", "200", "206", "210", "150", "206"));
                questionList.add(new QuestionModel("Gas we breathe?", "Oxygen", "CO2", "Nitrogen", "Helium", "Oxygen"));
                questionList.add(new QuestionModel("Earth is?", "Star", "Planet", "Moon", "Comet", "Planet"));
            }
        }

        // 👉 first question load
        setNextQuestion();

        // 👉 options click
        option1.setOnClickListener(v -> checkAnswer(option1.getText().toString()));
        option2.setOnClickListener(v -> checkAnswer(option2.getText().toString()));
        option3.setOnClickListener(v -> checkAnswer(option3.getText().toString()));
        option4.setOnClickListener(v -> checkAnswer(option4.getText().toString()));
    }

    private void setNextQuestion() {

        if (timer != null) timer.cancel();

        // 👉 Safety check
        if (questionList.isEmpty()) {
            question.setText("No Questions Available");
            return;
        }

        if (index < questionList.size()) {

            QuestionModel model = questionList.get(index);

            question.setText(model.question);
            option1.setText(model.option1);
            option2.setText(model.option2);
            option3.setText(model.option3);
            option4.setText(model.option4);

            // 👉 Timer
            int totalTime = 10;

            timer = new CountDownTimer(10000, 1000) {
                int timeLeft = totalTime;

                @Override
                public void onTick(long millisUntilFinished) {
                    timerText.setText("Time: " + timeLeft);
                    progressBar.setProgress(timeLeft);
                    timeLeft--;
                }

                @Override
                public void onFinish() {
                    progressBar.setProgress(0);
                    index++;
                    setNextQuestion();
                }
            }.start();
        } else {
            // 👉 RESULT PAGE OPEN
            String name = getSharedPreferences("quiz", MODE_PRIVATE)
                    .getString("username", "User");

            int finalScore = this.score;

            getSharedPreferences("quiz", MODE_PRIVATE)
                    .edit()
                    .putInt("lastScore", finalScore)
                    .apply();

            // leaderboard save
            String oldData = getSharedPreferences("quiz", MODE_PRIVATE)
                    .getString("leaderboard", "");
            String newData = oldData + name + ":" + finalScore + ",";
            getSharedPreferences("quiz", MODE_PRIVATE)
                    .edit()
                    .putString("leaderboard", newData)
                    .apply();

            // Score history save
            String oldHistory = getSharedPreferences("quiz", MODE_PRIVATE)
                    .getString("history", "");
            String newHistory = oldHistory + finalScore + ",";
            getSharedPreferences("quiz", MODE_PRIVATE)
                    .edit()
                    .putString("history", newHistory)
                    .apply();

            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("score", finalScore);
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer(String selectedAnswer) {
        if (index < questionList.size()) {
            if (selectedAnswer.equals(questionList.get(index).correctAns)) {
                score++;
            }
            index++;
            setNextQuestion();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) timer.cancel();
    }
}
