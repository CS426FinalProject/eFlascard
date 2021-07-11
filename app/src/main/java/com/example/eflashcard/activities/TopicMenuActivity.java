package com.example.eflashcard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eflashcard.R;

import java.util.ArrayList;
import java.util.List;

public class TopicMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_screen);
        List<Button> buttons = new ArrayList<>();
        buttons.add(findViewById(R.id.maths));
        buttons.add(findViewById(R.id.physics));
        buttons.add(findViewById(R.id.chemistry));

        for(Button b : buttons)
        {
            b.setOnClickListener(v -> {
                String type = getIntent().getStringExtra("TYPE");
                Intent intent;
                if(type.equals("STUDY"))
                    intent = new Intent(this, StudyActivity.class);
                // Đổi lại thành TestActivity hay gì đó
                // Nhớ thêm activity vào manifest
                else intent = new Intent(this, StudyActivity.class);
                intent.putExtra("TOPIC", b.getText().toString().toUpperCase());
                startActivity(intent);
            });
        }
    }

}
