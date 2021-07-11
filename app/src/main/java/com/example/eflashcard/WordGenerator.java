package com.example.eflashcard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordGenerator {
    public List<Word> generate() {
        List<Word> words = new ArrayList<>();
        words.add(new Word("Math", "Toan"));
        words.add(new Word("Literature", "Ngu van"));
        words.add(new Word("Java", "oop chua"));


        return words;
    }

    class generateMathWords extends WordGenerator {

        public List<Word> generate() {
            List<Word> words = new ArrayList<>();
            words.add(new Word("linear_function", "Phương trình tuyến tính"));
            words.add(new Word("integral", "nguyên phân"));
            words.add(new Word("implies", "suy ra"));
            words.add(new Word("matrix", "ma trận"));
            words.add(new Word("perpendicular", "vuông góc"));
            words.add(new Word("therefor", "do đó"));
            words.add(new Word("angel", "góc"));
            words.add(new Word("factorial", "giai thừa"));
            words.add(new Word("vector", "véc tơ"));
            words.add(new Word("square_root", "căn bậc 2"));


            return words;
        }

    }
}
