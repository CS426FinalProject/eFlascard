package com.example.eflashcard.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.eflashcard.adapters.FlashcardAdapter;
import com.example.eflashcard.R;
import com.example.eflashcard.generator.WordGenerator;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button study = findViewById(R.id.study);
        Button test = findViewById(R.id.test);
        study.setOnClickListener(v -> onClickHandler("STUDY"));
        test.setOnClickListener(v -> onClickHandler("TEST"));
    }

    // Type: study or test
    void onClickHandler(String type)
    {
        Intent intent = new Intent(this, TopicMenuActivity.class);
        intent.putExtra("TYPE", type);
        startActivity(intent);
    }
}