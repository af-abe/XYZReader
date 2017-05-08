package com.example.xyzreader.presenter.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by André on 05.05.2017.
 */

public class ArticleSplitItemPresenter extends BaseObservable {
    private final String mSplit;

    public ArticleSplitItemPresenter(String split) {
        mSplit = split;
    }

    @Bindable
    public String getSplit() {
        return mSplit;
    }
}
