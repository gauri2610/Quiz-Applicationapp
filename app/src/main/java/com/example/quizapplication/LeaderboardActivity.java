package com.example.quizapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        listView = findViewById(R.id.listView);
        list = new ArrayList<>();

        String data = getSharedPreferences("quiz", MODE_PRIVATE)
                .getString("leaderboard", "");

        String[] users = data.split(",");

        for (String u : users) {
            if (!u.isEmpty()) {
                list.add(u);
            }
        }

        // 👉 sort (highest score top)
        Collections.sort(list, (a, b) -> {
            int scoreA = Integer.parseInt(a.split(":")[1]);
            int scoreB = Integer.parseInt(b.split(":")[1]);
            return scoreB - scoreA;
        });

        // 👉 Top scorer mark
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.set(i, "🥇 " + list.get(i));
            }
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }
}