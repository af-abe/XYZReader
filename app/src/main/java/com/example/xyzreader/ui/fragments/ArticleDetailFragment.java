package com.example.xyzreader.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.xyzreader.R;
import com.example.xyzreader.databinding.FragmentArticleDetailBinding;
import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.persistence.RealmHelper;
import com.example.xyzreader.presenter.ArticleDetailPresenter;
import com.example.xyzreader.ui.adapter.GenericGridRecyclerAdapter;
import com.example.xyzreader.utils.DetailsTransition;


public class ArticleDetailFragment extends Fragment {
    private static final String ARGS_ARTICLE = "arg.article";

    public static ArticleDetailFragment newInstance(int article) {
        Bundle args = new Bundle();
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        args.putInt(ARGS_ARTICLE, article);
        fragment.setArguments(args);

        return fragment;
    }

    private ArticleDetailPresenter mPresenter;
    private IArticle mArticle;
    FragmentArticleDetailBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(new DetailsTransition());
            setEnterTransition(new Fade());
            setExitTransition(new Fade());
            setSharedElementReturnTransition(new DetailsTransition());
        }
        setAllowReturnTransitionOverlap(true);

        Bundle args = getArguments();

        if (args != null) {

            mArticle = RealmHelper.loadArticleById(args.getInt(ARGS_ARTICLE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_detail, container, false);
        mPresenter = new ArticleDetailPresenter(mArticle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.toolbarImage.setTransitionName("image" + mPresenter.getId());
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Glide.with(getContext())
                .load(mArticle.getPhoto())
                .centerCrop()
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        showContent();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        showContent();
                        return false;
                    }
                })
                .into(binding.toolbarImage);
    }

    private void showContent() {
        startPostponedEnterTransition();
        binding.setPresenter(mPresenter);

        setupRecyclerView(binding.article);
        binding.executePendingBindings();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new GenericGridRecyclerAdapter<>(mPresenter.getArticleSplits(), R.layout.item_article_split));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.saveState(outState);
        }
    }
}
