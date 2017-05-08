package com.example.xyzreader.logic;

import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.network.APIHelper;
import com.example.xyzreader.persistence.RealmHelper;

import java.util.List;

public class ArticleLogic {
    private static List<? extends IArticle> articles;
    public static List<? extends IArticle> getArticles() throws Exception {
        if(articles == null) {
            articles = APIHelper.getArticle();
            RealmHelper.persistArticel(articles);
        }
        return articles;
    }

    public static boolean articlesInMemory(){
        return articles != null;
    }
}
