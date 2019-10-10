package com.example.testiq.ui.exam;

public class Exam {
    private String content;
    private int hinh;
    private int time;
    private int exam_id;

    public Exam() {
    }

    public Exam(String content, int hinh, int time, int exam_id) {
        this.content = content;
        this.hinh = hinh;
        this.time = time;
        this.exam_id = exam_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
