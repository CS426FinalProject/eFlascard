package com.example.eflashcard;

import java.util.ArrayList;
import java.util.List;

public class WordGenerator {
    public List<Word> generate()
    {
        List<Word> words = new ArrayList<>();
        words.add(new Word("Math", "Toan"));
        words.add(new Word("Literature", "Ngu van"));
        words.add(new Word("Java", "oop chua"));
        return words;
    }
}
