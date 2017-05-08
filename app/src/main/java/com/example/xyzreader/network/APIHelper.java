package com.example.xyzreader.network;

import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.network.models.APIArticle;
import com.example.xyzreader.network.models.ArticleEndPoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    public static List<? extends IArticle> getArticle() throws Exception {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss")
                .create();

        ArticleEndPoint service = new Retrofit.Builder()
                .baseUrl("https://go.udacity.com")
                .addConverterFactory((GsonConverterFactory.create(gson)))
                .build()
                .create(ArticleEndPoint.class);

        Response<List<APIArticle>> response = service.getArticles().execute();
        return response.body();
    }
}
