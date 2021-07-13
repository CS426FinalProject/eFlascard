package com.example.eflashcard.models;

public class Result {
    boolean status;
    boolean hintUsed;

    public boolean getStatus() {
        return status;
    }

    public boolean isHintUsed() {
        return hintUsed;
    }

    public Result(boolean status, boolean hintUsed) {
        this.status = status;
        this.hintUsed = hintUsed;
    }
}
