package com.example.xyzreader.presenter.models;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.example.xyzreader.databinding.ItemListArticleBinding;
import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.ui.activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ArticleListItemPresenter extends BaseObservable implements Parcelable {

    private final String mImageUrl;
    private final String mTitle;
    private final Date mDate;
    private final String mAuthor;

    private final int mArticleId;

    @Bindable
    public String getImageUrl() {
        return mImageUrl;
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    @Bindable
    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM yyyy", Locale.getDefault());
        return sdf.format(mDate);
    }

    public int getId(){
        return mArticleId;
    }

    @Bindable
    public String getAuthor() {
        return "by " + mAuthor;
    }

    public void onOpenArticle(Context context, View v){
        if(context instanceof MainActivity){
            ItemListArticleBinding binding = DataBindingUtil.getBinding(v);
            ((MainActivity) context).openArticle(mArticleId, binding.image);
        }
    }

    public ArticleListItemPresenter(IArticle model) {
        mImageUrl = model.getThumb();
        mTitle = model.getTitle();
        mDate = model.getPublishedDate();
        mAuthor = model.getAuthor();

        mArticleId = model.getId();
    }

    private ArticleListItemPresenter(Parcel in) {
        mImageUrl = in.readString();
        mTitle = in.readString();
        mDate = new Date(in.readLong());
        mAuthor = in.readString();
        mArticleId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImageUrl);
        dest.writeString(mTitle);
        dest.writeLong(mDate.getTime());
        dest.writeString(mAuthor);
        dest.writeInt(mArticleId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ArticleListItemPresenter> CREATOR = new Creator<ArticleListItemPresenter>() {
        @Override
        public ArticleListItemPresenter createFromParcel(Parcel in) {
            return new ArticleListItemPresenter(in);
        }

        @Override
        public ArticleListItemPresenter[] newArray(int size) {
            return new ArticleListItemPresenter[size];
        }
    };

}
