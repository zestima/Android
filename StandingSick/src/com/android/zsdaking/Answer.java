package com.android.zsdaking;

public class Answer {

    int id;
    String answer;

    public Answer(String q) {
        this.answer = q;

    }

    public Answer(int id, String q) {
        this.id = id;
        this.answer = q;
    }

    public int getID() {
        return this.id;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String q) {
        this.answer = q;
    }
}
