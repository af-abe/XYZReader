package com.example.xyzreader.persistence;

import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.persistence.models.DbArticle;

import java.util.List;

import io.realm.Realm;

public class RealmHelper {
    public static void persistArticel(List<? extends IArticle> articles) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> {
            for(IArticle article : articles){
                realm1.copyToRealm(new DbArticle(article));
            }
        });
        realm.close();
    }

    public static IArticle loadArticleById(int id) {
        Realm realm = Realm.getDefaultInstance();
        IArticle result = null;
        DbArticle dbModel = realm.where(DbArticle.class).equalTo("id", id).findFirst();
        if(dbModel != null){
            result = realm.copyFromRealm(dbModel);
        }

        return result;
    }
}
