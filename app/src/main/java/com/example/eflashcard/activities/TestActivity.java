package com.example.eflashcard.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.eflashcard.R;
import com.example.eflashcard.adapters.FlashcardTestAdapter;
import com.example.eflashcard.generator.WordGenerator;
import com.example.eflashcard.models.Result;
import com.example.eflashcard.models.Word;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        WordGenerator generator = new WordGenerator();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        RecyclerView recyclerView = findViewById(R.id.list_word);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false));

        String topic = getIntent().getStringExtra("TOPIC");
        List<Word> vocabs;
        if (topic.equals("MATHS"))
            vocabs = generator.generateMathWords();
        else if (topic.equals("PHYSICS"))
            vocabs = generator.generatePhysicsWords();
        else
            vocabs = generator.generateChemistryWords();

        List<Result> results = new ArrayList<>();
        FlashcardTestAdapter adapter = new FlashcardTestAdapter(vocabs, this);
        adapter.setOnSubmitListener(r -> {
            results.add(r);
            if(results.size() == vocabs.size())
            {
                float score = 0;
                for(Result result : results)
                {
                    if(result.getStatus())
                        score += 1.0 / (result.isHintUsed() ? 2 : 1);
                }
                new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage("Final score: " + score +'/'+ results.size())
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    finish();
                }).show();
            }
        });
        recyclerView.setAdapter(adapter);

        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                hideKeyboard(TestActivity.this);
            }
        });
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
