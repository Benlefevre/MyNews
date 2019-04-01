package com.benlefevre.mynews.controllers.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.models.Article;
import com.benlefevre.mynews.utils.NyTimesStream;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends androidx.fragment.app.Fragment {

    private static final String POSITION = "position";
    @BindView(R.id.article_fragment_recycler_view)
    RecyclerView mArticleFragmentRecyclerView;
    @BindView(R.id.article_fragment_swipe_layout)
    SwipeRefreshLayout mArticleFragmentSwipeLayout;

    private int position;
    private Disposable mDisposable;

    public ArticleFragment() {
        // Required empty public constructor
    }

    /**
     * A factory to create new ArticleFragment with its position in the ViewPager saved in args
     * @param position the ArticleFragment's position in the ViewPager
     * @return the new fragment with its position saved in args
     */
    public static androidx.fragment.app.Fragment newInstance(int position) {
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
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
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);
        executeHttpRequestAccordindToPosition(position);
        configureSwipeRefreshLayout();
        return view;
    }

    /**
     * Configures OnRefresh to make the correct http request according to the ArticleFragment's position
     * in the ViewPager when user swipe from top to down.
     */
    private void configureSwipeRefreshLayout() {
        mArticleFragmentSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestAccordindToPosition(position);
            }
        });
    }

    /**
     * Executes the correct http request according to the ArticleFragment's position in the ViewPager
     * @param position ArticleFragment's position in the ViewPager
     */
    private void executeHttpRequestAccordindToPosition(int position) {
        switch (position) {
            case 0:
                executeTopStoriesHttpRequest();
                break;
            case 1:
                executeMostPopularHttpRequest();
                break;
            case 2:
                executeAutomobileHttpRequest();
                break;
        }
    }

    /**
     * Subscribes to the Observable returned by NyTimesStream method and update UI when onNext is called
     */
    private void executeAutomobileHttpRequest() {
        mDisposable = NyTimesStream.streamFetchTopStoriesArticles("automobiles")
                .subscribeWith(new DisposableObserver<Article>() {
                    @Override
                    public void onNext(Article article) {
                        mArticleFragmentSwipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * Subscribes to the Observable returned by NyTimesStream method and update UI when onNext is called
     */
    private void executeMostPopularHttpRequest() {
        mDisposable = NyTimesStream.streamFetchMostPopularArticles()
                .subscribeWith(new DisposableObserver<Article>() {
                    @Override
                    public void onNext(Article article) {
                        mArticleFragmentSwipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * Subscribes to the Observable returned by NyTimesStream method and update UI when onNext is called
     */
    private void executeTopStoriesHttpRequest() {
        mDisposable = NyTimesStream.streamFetchTopStoriesArticles("home")
                .subscribeWith(new DisposableObserver<Article>() {
                    @Override
                    public void onNext(Article article) {
                        mArticleFragmentSwipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * Unsubscribe to the Observable when the fragment is destroyed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }
}

