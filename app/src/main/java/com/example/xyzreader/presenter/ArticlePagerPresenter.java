package com.example.xyzreader.presenter;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.support.v4.app.LoaderManager;

import com.example.xyzreader.logic.ArticleLogic;
import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.ui.fragments.ArticlePagerFragment;
import com.example.xyzreader.utils.AsyncOperation;


public class ArticlePagerPresenter extends BasePresenter {
    private ArticlePagerFragment parent;
    private final ObservableArrayList<IArticle> mItems = new ObservableArrayList<>();

    public ArticlePagerPresenter(ArticlePagerFragment parent) {
        this.parent = parent;
    }

    public void loadArticles(Context context, LoaderManager loaderManager) {
        try {
            mItems.addAll(ArticleLogic.getArticles());
        } catch (Exception e) {
            e.printStackTrace();
        }
        parent.setupPager();
    }

    public ObservableArrayList<IArticle> getItems() {
        return mItems;
    }

    public void destroy(){
        parent = null;
        mItems.clear();
    }
}
