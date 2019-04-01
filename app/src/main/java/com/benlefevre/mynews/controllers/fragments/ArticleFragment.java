package com.benlefevre.mynews.controllers.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benlefevre.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends androidx.fragment.app.Fragment {

    public static final String POSITION = "position";
    private int position;

    public ArticleFragment() {
        // Required empty public constructor
    }

    /**
     * A factory to create new ArticleFragment with its position in the ViewPager saved in args
     * @param position the ArticleFragment's position in the ViewPager
     * @return the new fragment with its position saved in args
     */
    public static androidx.fragment.app.Fragment newInstance(int position){
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION,position);
        articleFragment.setArguments(args);
        return articleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

}
