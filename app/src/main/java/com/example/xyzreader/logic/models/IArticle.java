package com.example.xyzreader.logic.models;

import android.os.Parcelable;

import java.util.Date;

/**
 * Created by André on 05.05.2017.
 */

public interface IArticle{
    public Integer getId();

    public String getTitle();

    public String getAuthor();

    public String getBody();

    public String getThumb();

    public String getPhoto();

    public Double getAspectRatio();

    public Date getPublishedDate();
}
