package com.example.xyzreader.presenter;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.example.xyzreader.R;
import com.example.xyzreader.logic.ArticleLogic;
import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.presenter.models.ArticleListItemPresenter;
import com.example.xyzreader.utils.AsyncOperation;

import java.util.ArrayList;
import java.util.List;

public class ArticleListPresenter extends BasePresenter {
    private static final int LOADER_ID = 0;
    private final ObservableArrayList<ArticleListItemPresenter> mItems = new ObservableArrayList<>();
    public final ObservableInt mLoadingVisibility = new ObservableInt(View.GONE);

    public void loadArticles(final Context context, LoaderManager supportLoaderManager) {
        mLoadingVisibility.set(View.VISIBLE);
        if(ArticleLogic.articlesInMemory()){
            try {
                setContent(ArticleLogic.getArticles());
            } catch (Exception e) {
                showError(context, supportLoaderManager, e);
            }
        } else {

            new AsyncOperation<>(context, supportLoaderManager, LOADER_ID, ArticleLogic::getArticles)
                    .setOnSuccess(this::setContent)
                    .setOnError(throwable -> showError(context, supportLoaderManager, throwable))
                    .execute();
        }
    }

    private void showError(Context context, LoaderManager supportLoaderManager, Throwable throwable) {
        mLoadingVisibility.set(View.GONE);
        new AlertDialog.Builder(context)
                .setMessage(R.string.error_dialog_msg)
                .setTitle(R.string.error_dialog_title)
                .setPositiveButton(R.string.error_dialog_button, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    loadArticles(context, supportLoaderManager);
                }).setCancelable(false)
                .show();

        throwable.printStackTrace();
    }

    private void setContent(List<? extends IArticle> data) {
        mLoadingVisibility.set(View.GONE);
        mItems.clear();
        for (IArticle article : data) {
            mItems.add(new ArticleListItemPresenter(article));
        }
    }

    public ObservableArrayList<ArticleListItemPresenter> getItems() {
        return mItems;
    }

    @Override
    public void saveState(Bundle out) {
        out.putParcelableArrayList(ArticleListItemPresenter.class.getCanonicalName(), mItems);
    }

    @Override
    public void restoreState(Bundle state) {
        ArrayList<ArticleListItemPresenter> arrayList = state.getParcelableArrayList(ArticleListItemPresenter.class.getCanonicalName());
        if (arrayList != null) {
            mItems.addAll(arrayList);
        }
    }
}
