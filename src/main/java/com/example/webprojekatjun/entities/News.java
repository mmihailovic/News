package com.example.webprojekatjun.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class News {
    private Integer id;

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    private String title;
    @NotNull(message = "Content is required")
    @NotEmpty(message = "Content is required")
    private String text;
    @NotNull(message = "Date is required")
    @NotEmpty(message = "Date is required")
    private long date;
    private int brojPoseta;

    @NotNull(message = "Author is required")
    @NotEmpty(message = "Author is required")
    private String author;

    @NotNull(message = "Category is required")
    @NotEmpty(message = "Category is required")
    private String category;

    public News() {
    }

    public News(String title, String text, long date, int brojPoseta, String author, String category) {
        this.title = title;
        this.date = date;
        this.author = author;
        this.text = text;
        this.category = category;
        this.brojPoseta = brojPoseta;
    }

    public News(Integer id, String title, String text, long date, int brojPoseta, String author, String category) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.author = author;
        this.text = text;
        this.brojPoseta = brojPoseta;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getBrojPoseta() {
        return brojPoseta;
    }

    public void setBrojPoseta(int brojPoseta) {
        this.brojPoseta = brojPoseta;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
