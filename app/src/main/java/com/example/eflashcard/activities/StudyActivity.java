package com.example.eflashcard.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.eflashcard.R;
import com.example.eflashcard.adapters.FlashcardAdapter;
import com.example.eflashcard.generator.WordGenerator;
import com.example.eflashcard.models.Word;

import java.util.List;

public class StudyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        WordGenerator generator = new WordGenerator();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_activity);
        RecyclerView recyclerView = findViewById(R.id.list_word);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false));

        String topic = getIntent().getStringExtra("TOPIC");
        List<Word> vocabs;
        if(topic.equals("MATHS"))
            vocabs = generator.generateMathWords();
        else if(topic.equals("PHYSICS"))
            vocabs = generator.generatePhysicsWords();
        else vocabs = generator.generateChemistryWords();

        recyclerView.setAdapter(new FlashcardAdapter(vocabs,this));
        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recyclerView);
    }
}
