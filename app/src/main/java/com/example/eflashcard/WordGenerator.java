package com.example.eflashcard;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class WordGenerator {
    public List<Word> generateChemistryWords() {
        List<Word> words = new ArrayList<>();
        words.add(new Word("Acid", "Axit"));
        words.add(new Word("Atom", "Nguyên tử"));
        words.add(new Word("Balance", "Cân bằng"));
        words.add(new Word("Base", "Bazơ"));
        words.add(new Word("Calcium", "Canxi"));
        words.add(new Word("Chlorine", "Clo"));
        words.add(new Word("Evaporation", "Sự bay hơi"));
        words.add(new Word("Ionization", "Sự ion hóa"));
        words.add(new Word("Polymerization", "Sự trùng hợp"));
        words.add(new Word("Precipitation", "Sự kết tủa"));
        return words;
    }
}
