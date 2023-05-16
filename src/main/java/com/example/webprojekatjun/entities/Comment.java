package com.example.webprojekatjun.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Comment {
    private Integer id;

    @NotNull(message = "Post id is required")
    @NotEmpty(message = "Post id is required")
    private Integer vest_id;

    @NotEmpty(message = "Text is required")
    @NotNull(message = "Text is required")
    private String text;

    @NotEmpty(message = "Author is required")
    @NotNull(message = "Author is required")
    private String autor;

    @NotEmpty(message = "Author is required")
    @NotNull(message = "Author is required")
    private long date;



    public Comment() {
    }

    public Comment(Integer vest_id, String text, String autor, long date) {
        this.vest_id = vest_id;
        this.text = text;
        this.autor = autor;
        this.date = date;
    }

    public Comment(Integer id, Integer vest_id, String text, String autor, long date) {
        this.id = id;
        this.vest_id = vest_id;
        this.text = text;
        this.autor = autor;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVest_id() {
        return vest_id;
    }

    public void setVest_id(Integer vest_id) {
        this.vest_id = vest_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
