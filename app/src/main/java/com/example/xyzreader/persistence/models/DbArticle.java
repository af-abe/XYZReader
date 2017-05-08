package com.example.xyzreader.persistence.models;

import com.example.xyzreader.logic.models.IArticle;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DbArticle extends RealmObject implements IArticle {

    @PrimaryKey
    private Integer id;
    private String title;
    private String author;
    private String body;
    private String thumb;
    private String photo;
    private Double aspectRatio;
    private Date publishedDate;

    public DbArticle() {
    }

    public DbArticle(IArticle model) {
        id = model.getId();
        title = model.getTitle();
        author = model.getAuthor();
        body = model.getBody();
        thumb = model.getThumb();
        photo = model.getPhoto();
        aspectRatio = model.getAspectRatio();
        publishedDate = model.getPublishedDate();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getThumb() {
        return thumb;
    }

    public String getPhoto() {
        return photo;
    }

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }
}
