package com.example.xyzreader.ui.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.R;
import com.example.xyzreader.databinding.FragmentArticleListBinding;
import com.example.xyzreader.presenter.ArticleListPresenter;
import com.example.xyzreader.ui.adapter.GenericGridRecyclerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleListFragment extends Fragment {
    ArticleListPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        FragmentArticleListBinding binding = DataBindingUtil.bind(view);
        mPresenter = new ArticleListPresenter();
        binding.setPresenter(mPresenter);

        if (savedInstanceState == null) {
            mPresenter.loadArticles(getContext(), getLoaderManager());
        } else {
            mPresenter.restoreState(savedInstanceState);
        }

        setupGridView(binding.articleList);

        return view;
    }

    private void setupGridView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new GenericGridRecyclerAdapter<>(mPresenter.getItems(), R.layout.item_list_article));

        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), getResources().getInteger(R.integer.grid_colums)));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.saveState(outState);
    }
}
