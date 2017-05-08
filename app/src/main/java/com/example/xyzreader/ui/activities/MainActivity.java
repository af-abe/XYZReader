package com.example.xyzreader.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.xyzreader.R;
import com.example.xyzreader.logic.models.IArticle;
import com.example.xyzreader.ui.fragments.ArticleDetailFragment;
import com.example.xyzreader.ui.fragments.ArticleListFragment;
import com.example.xyzreader.ui.fragments.ArticlePagerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            tr.add(R.id.container, new ArticleListFragment());
            tr.commit();
        }
    }

    public void openArticle(int id, View image) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.addSharedElement(image, "image"+id);
        tr.replace(R.id.container, ArticlePagerFragment.newInstance(id));
        tr.addToBackStack(null);
        tr.commit();
    }


    @Override
    public void onBackPressed() {
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
        }
    }
}
