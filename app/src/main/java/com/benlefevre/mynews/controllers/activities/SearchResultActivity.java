package com.benlefevre.mynews.controllers.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.adapters.ArticleAdapter;
import com.benlefevre.mynews.models.Article;
import com.benlefevre.mynews.utils.Constants;
import com.benlefevre.mynews.utils.NyTimesStream;
import com.benlefevre.mynews.utils.Utils;
import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class SearchResultActivity extends AppCompatActivity {

    @BindView(R.id.search_result_swipe_layout)
    SwipeRefreshLayout mSearchResultSwipeLayout;
    @BindView(R.id.search_result_recycler_view)
    RecyclerView mSearchResultRecyclerView;

    private Map<String, String> mQueries;
    private Disposable mDisposable;
    private List<Article.Doc> mDocList;
    private ArticleAdapter mArticleAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        mContext = this;
        configureToolBar();
        configureSwipeRefreshLayout();
        configureRecyclerView();
        executeSearchHttpRequest();
    }

    /**
     * Configures toolbar as ActionBar and allows to display Up item
     */
    private void configureToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.result_search_toolbar_title));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Adds a divider item decoration in the recycler view between each items and set the adapter
     */
    private void configureRecyclerView() {
        mDocList = new ArrayList<>();
        mArticleAdapter = new ArticleAdapter(mDocList, Glide.with(this));
        mSearchResultRecyclerView.setAdapter(mArticleAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        mSearchResultRecyclerView.addItemDecoration(dividerItemDecoration);
        mSearchResultRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Configures OnRefresh to make the http request when user swipe from top to down.
     */
    private void configureSwipeRefreshLayout() {
        mSearchResultSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeSearchHttpRequest();
            }
        });
    }

    /**
     * Fetch all the IntentExtra (from SearchActivity) to set the QueryMap needed to execute the
     * Http request on searchAPI.
     */
    private void getIntentExtra() {
        mQueries = new HashMap<>();
        Intent intent = getIntent();
        String query = intent.getStringExtra(Constants.QUERY);
        mQueries.put("q", query);
        String filterQuery = intent.getStringExtra(Constants.FILTERQUERY);
        mQueries.put("fq", filterQuery);
        String beginDate = null;
        String endDate = null;
        if (!intent.getStringExtra(Constants.BEGINDATE).isEmpty()) {
            beginDate = Utils.convertDateForQuery(intent.getStringExtra(Constants.BEGINDATE));
            mQueries.put("begin_date", beginDate);
        }
        if (!intent.getStringExtra(Constants.ENDDATE).isEmpty()) {
            endDate = Utils.convertDateForQuery(intent.getStringExtra(Constants.ENDDATE));
            mQueries.put("end_date", endDate);
        }

    }

    /**
     * Subscribes to the Observable returned by NyTimesStream method and update UI when onNext is called
     */
    private void executeSearchHttpRequest() {
        getIntentExtra();
        mDisposable = NyTimesStream.streamFetchSearchArticles(mQueries)
                .subscribeWith(new DisposableObserver<Article>() {
                    @Override
                    public void onNext(Article article) {
                        if (article.getResponse().getDocs().size() != 0) {
                            mSearchResultSwipeLayout.setRefreshing(false);
                            mDocList.clear();
                            mDocList.addAll(article.getResponse().getDocs());
                            mArticleAdapter.notifyDataSetChanged();
                        } else {
                            displayAlertDialogIfNoResult();
                        }
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
     * Displays a MaterialAlertDialog if there is no results matched the user's research
     */
    private void displayAlertDialogIfNoResult() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(mContext);
        builder.setTitle("Sorry")
                .setMessage("There is no news matched your search. Please, modify your search and try again")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
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