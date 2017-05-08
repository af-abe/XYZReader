package com.example.xyzreader.presenter;

import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.presenter.models.ArticleSplitItemPresenter;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ArticleDetailPresenter extends BasePresenter {

    private final IArticle mArticle;
    private final ObservableArrayList<ArticleSplitItemPresenter> mArticleSplits = new ObservableArrayList<>();

    public ArticleDetailPresenter(IArticle article) {
        mArticle = article;
        //try to format the Text and split it, because it is too big for one TextView
        for (String split : article.getBody().split("\r\n\r\n")) {
            mArticleSplits.add(new ArticleSplitItemPresenter(split.replace("\r\n", " ").trim()));
        }
    }

    public ObservableArrayList<ArticleSplitItemPresenter> getArticleSplits() {
        return mArticleSplits;
    }

    @Bindable
    public String getImageUrl() {
        return mArticle.getPhoto();
    }

    @Bindable
    public String getTitle() {
        return mArticle.getTitle();
    }

    @Bindable
    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM yyyy", Locale.getDefault());
        return sdf.format(mArticle.getPublishedDate());
    }

    public int getId(){
        return mArticle.getId();
    }

    @Bindable
    public String getAuthor() {
        return "by " + mArticle.getAuthor();
    }

    public void share(Context context) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, mArticle.getBody().substring(0, Math.min(10000, mArticle.getBody().length())));
        i.setType("text/plain");

        context.startActivity(i);
    }
}
