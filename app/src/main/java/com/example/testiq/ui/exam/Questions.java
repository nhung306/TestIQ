package com.example.testiq.ui.exam;

public class Questions {
    private int ans_right;
    private String answers1;
    private String answers2;
    private String answers3;
    private String answers4;
    private String content;
    private int exam_id;

    public Questions(int ans_right, String answers1, String answers2, String answers3, String answers4, String content, int exam_id) {
        this.ans_right = ans_right;
        this.answers1 = answers1;
        this.answers2 = answers2;
        this.answers3 = answers3;
        this.answers4 = answers4;
        this.content = content;
        this.exam_id = exam_id;
    }

    public Questions() {
    }

    public int getAns_right() {
        return ans_right;
    }

    public String getAnswers1() {
        return answers1;
    }

    public void setAnswers1(String answers1) {
        this.answers1 = answers1;
    }

    public String getAnswers2() {
        return answers2;
    }

    public void setAnswers2(String answers2) {
        this.answers2 = answers2;
    }

    public String getAnswers3() {
        return answers3;
    }

    public void setAnswers3(String answers3) {
        this.answers3 = answers3;
    }

    public String getAnswers4() {
        return answers4;
    }

    public void setAnswers4(String answers4) {
        this.answers4 = answers4;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setAns_right(int ans_right) {
        this.ans_right = ans_right;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }
}

