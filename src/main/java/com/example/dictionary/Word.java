package com.example.dictionary;

public class Word {
    private String word_target;
    private String word_type;
    private String word_pronun;
    private String word_explain;

    //Work target
    public void setWord_target(String word_target) {
        this.word_target = word_target ;
    }

    public String getWord_target(){
        return word_target;
    }

    //Word type
    public void setWord_type(String word_type) {
        this.word_type = word_type;
    }
    public String getWord_type() {
        return word_type;
    }

    //Word pronun
    public void setWord_pronun(String word_pronun) {
        this.word_pronun = word_pronun;
    }

    public String getWord_pronun() {
        return word_pronun;
    }

    //Word explain
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain ;
    }

    public String getWord_explain() {
        return word_explain;
    }

    //Constructor
    public Word(String word_target, String word_type, String word_pronun, String word_explain) {
        this.word_target = word_target;
        this.word_type = word_type;
        this.word_pronun = word_pronun;
        this.word_explain = word_explain;
    }
}

