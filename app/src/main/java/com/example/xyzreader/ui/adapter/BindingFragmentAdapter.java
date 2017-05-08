package com.example.xyzreader.ui.adapter;

import android.databinding.ObservableList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.ui.fragments.ArticleDetailFragment;

public class BindingFragmentAdapter extends FragmentStatePagerAdapter {

    private SparseArray<Fragment> mFragments;
    private ObservableList<? extends IArticle> mItems;

    public BindingFragmentAdapter(FragmentManager fm, ObservableList<? extends IArticle> items) {
        super(fm);
        mFragments = new SparseArray<>();
        if (items == null) {
            throw new IllegalArgumentException(getClass().getName() + ": items must not be null.");
        }
        mItems = items;
        //noinspection unchecked
        mItems.addOnListChangedCallback(new ObservableList.OnListChangedCallback() {

            @Override
            public void onChanged(ObservableList observableList) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList observableList, int i, int i1) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeInserted(ObservableList observableList, int i, int i1) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeMoved(ObservableList observableList, int i, int i1, int i2) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeRemoved(ObservableList observableList, int i, int i1) {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            fragment = ArticleDetailFragment.newInstance(mItems.get(position).getId());
            mFragments.put(position, fragment);
        }

        return fragment;
    }


    @Override
    public int getCount() {
        return mItems.size();
    }
}
