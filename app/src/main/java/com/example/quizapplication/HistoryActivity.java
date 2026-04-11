package com.example.quizapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class HistoryActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.listView);
        list = new ArrayList<>();

        String data = getSharedPreferences("quiz", MODE_PRIVATE)
                .getString("history", "");

        String[] scores = data.split(",");

        int attempt = 1;

        for (String s : scores) {
            if (!s.isEmpty()) {
                list.add("Attempt " + attempt + " → " + s + " marks");
                attempt++;
            }
        }

        // 👉 Latest attempt top ला
        Collections.reverse(list);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }
}