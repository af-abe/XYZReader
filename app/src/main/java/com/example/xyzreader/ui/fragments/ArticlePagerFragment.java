package com.example.xyzreader.ui.fragments;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.R;
import com.example.xyzreader.databinding.FragmentArticlePagerBinding;
import com.example.xyzreader.logic.ArticleLogic;
import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.presenter.ArticlePagerPresenter;
import com.example.xyzreader.ui.adapter.BindingFragmentAdapter;
import com.example.xyzreader.utils.DetailsTransition;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlePagerFragment extends Fragment {

    private static final String ARGS_ARTICLE_ID = "args.articleId";
    private FragmentArticlePagerBinding mBinding;

    public static ArticlePagerFragment newInstance(int articleId) {
        Bundle args = new Bundle();
        args.putInt(ARGS_ARTICLE_ID, articleId);
        ArticlePagerFragment fragment = new ArticlePagerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(new DetailsTransition());
            setEnterTransition(new Fade());
            setExitTransition(new Fade());
            setSharedElementReturnTransition(new DetailsTransition());
        }
        setAllowReturnTransitionOverlap(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_pager, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupPager();
    }

    public void setupPager() {
        int targetId = getArguments().getInt(ARGS_ARTICLE_ID, -1);

        ObservableArrayList<IArticle> list = new ObservableArrayList<>();
        try {
            list.addAll(ArticleLogic.getArticles());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int targetIndex = 0;
        if (targetId > -1) {
            for (IArticle article : list) {
                if (article.getId() == targetId) {
                    targetIndex = list.indexOf(article);
                    break;
                }
            }
        }
        mBinding.pager.setAdapter(new BindingFragmentAdapter(getChildFragmentManager(), list));
        mBinding.pager.setCurrentItem(targetIndex);
    }
}
