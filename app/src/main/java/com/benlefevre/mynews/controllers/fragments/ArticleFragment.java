package com.benlefevre.mynews.controllers.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.adapters.ArticleAdapter;
import com.benlefevre.mynews.controllers.activities.DisplayArticleActivity;
import com.benlefevre.mynews.models.Article;
import com.benlefevre.mynews.utils.ItemClickSupport;
import com.benlefevre.mynews.utils.NyTimesStream;
import com.benlefevre.mynews.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.benlefevre.mynews.utils.Constants.ID;
import static com.benlefevre.mynews.utils.Constants.MOSTPOPULAR;
import static com.benlefevre.mynews.utils.Constants.POSITION;
import static com.benlefevre.mynews.utils.Constants.TOPSTORIES;
import static com.benlefevre.mynews.utils.Constants.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends androidx.fragment.app.Fragment {

    @BindView(R.id.article_fragment_recycler_view)
    RecyclerView mArticleFragmentRecyclerView;
    @BindView(R.id.article_fragment_swipe_layout)
    SwipeRefreshLayout mArticleFragmentSwipeLayout;

    private int position;
    private Disposable mDisposable;
    private ArticleAdapter mArticleAdapter;
    private List<Article.Result> mResultList;

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
        configureRecyclerView();
        configureOnClickItemRecyclerView();
        return view;
    }

    /**
     * Adds a divider item decoration in the recycler view between each items and set the correct adapter
     * according to the Api used
     */
    private void configureRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mArticleFragmentRecyclerView.getContext(),layoutManager.getOrientation());
        mArticleFragmentRecyclerView.addItemDecoration(dividerItemDecoration);
        mArticleFragmentRecyclerView.setLayoutManager(layoutManager);
        switch(position){
            case 0:
                mResultList = new ArrayList<>();
                mArticleAdapter = new ArticleAdapter(mResultList, Glide.with(this),TOPSTORIES);
                mArticleFragmentRecyclerView.setAdapter(mArticleAdapter);
                break;
            case 1:
                mResultList = new ArrayList<>();
                mArticleAdapter = new ArticleAdapter(mResultList, Glide.with(this),MOSTPOPULAR);
                mArticleFragmentRecyclerView.setAdapter(mArticleAdapter);
                break;
            case 2:
                mResultList = new ArrayList<>();
                mArticleAdapter = new ArticleAdapter(mResultList, Glide.with(this),TOPSTORIES);
                mArticleFragmentRecyclerView.setAdapter(mArticleAdapter);
                break;
        }

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
                        mResultList.clear();
                        mResultList.addAll(article.getResults());
                        mArticleAdapter.notifyDataSetChanged();
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
                        mResultList.clear();
                        mResultList.addAll(article.getResults());
                        mArticleAdapter.notifyDataSetChanged();
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
                        mResultList.clear();
                        mResultList.addAll(article.getResults());
                        mArticleAdapter.notifyDataSetChanged();
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
     * Adds to the RecyclerView the support of the user's item click.
     */
    private void configureOnClickItemRecyclerView(){
        ItemClickSupport.addTo(mArticleFragmentRecyclerView,R.id.item_layout)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(getActivity(), DisplayArticleActivity.class);
                        intent.putExtra(URL,mArticleAdapter.getUrl(position));
                        intent.putExtra(ID, Utils.convertTitleToId(mArticleAdapter.getTitle(position)));
                        startActivity(intent);
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

