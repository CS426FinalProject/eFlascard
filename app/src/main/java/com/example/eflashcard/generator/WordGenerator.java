package com.example.eflashcard.generator;

import com.example.eflashcard.models.Word;

import java.util.ArrayList;
import java.util.List;

public class WordGenerator {
        public List<Word> generateMathWords() {
            List<Word> words = new ArrayList<>();
            words.add(new Word("Linear function", "Phương trình tuyến tính"));
            words.add(new Word("Integral", "Nguyên phân"));
            words.add(new Word("Implies", "Suy ra"));
            words.add(new Word("Matrix", "Ma trận"));
            words.add(new Word("Perpendicular", "Vuông góc"));
            words.add(new Word("Therefore", "Do đó"));
            words.add(new Word("Angle", "Góc"));
            words.add(new Word("Factorial", "Giai thừa"));
            words.add(new Word("Vector", "Véc tơ"));
            words.add(new Word("Square root", "Căn bậc 2"));
            return words;
        }

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

    public List<Word> generatePhysicsWords() {
        List<Word> words = new ArrayList<>();
        words.add(new Word("Mass", "Khối lượng"));
        words.add(new Word("Gravity", "Trọng lực"));
        words.add(new Word("Friction", "Sự ma sát"));
        words.add(new Word("Collision", "Va chạm"));
        words.add(new Word("Magnet", "Nam châm"));
        words.add(new Word("Speed", "Tốc độ"));
        words.add(new Word("Temperature", "Nhiệt độ"));
        words.add(new Word("Momentum", "Mô-men"));
        words.add(new Word("Torque", "Mô-men lực"));
        words.add(new Word("Distance", "Khoảng cách"));
        return words;
    }
}
