package com.example.xyzreader.network.models;

import android.os.Parcel;

import com.example.xyzreader.logic.models.IArticle;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class APIArticle implements IArticle {
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("body")
    private String body;
    @SerializedName("thumb")
    private String thumb;
    @SerializedName("photo")
    private String photo;
    @SerializedName("aspect_ratio")
    private Double aspectRatio;
    @SerializedName("published_date")
    private Date publishedDate;

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
