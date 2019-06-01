package com.punchips.relatable;

public class Post {

    private String id;

    private String text;
    private int yes;
    private int no;

    public Post() {
    }

    public Post(String id, String text, int yes, int no) {
        this.id = id;
        this.text = text;
        this.yes = yes;
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getYes() {
        return yes;
    }

    public void setYes(int yes) {
        this.yes = yes;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
