package com.android.zsdaking;

public class Question {

    int id;
    String question;

    public Question(String q) {
        this.question = q;

    }

    public Question(int id, String q) {
        this.id = id;
        this.question = q;
    }

    public int getID() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String q) {
        this.question = q;
    }
}
