package com.example.xyzreader.network.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleEndPoint {
    @GET("/xyz-reader-json")
    Call<List<APIArticle>> getArticles();
}
