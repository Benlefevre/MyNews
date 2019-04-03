package com.benlefevre.mynews.controllers.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.adapters.ArticleAdapter;
import com.benlefevre.mynews.models.Article;
import com.benlefevre.mynews.utils.Constants;
import com.benlefevre.mynews.utils.NyTimesStream;
import com.benlefevre.mynews.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        configureToolBar();
        configureSwipeRefreshLayout();
        configureRecyclerView();
        executeSearchHttpRequest();
    }

    private void configureToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.result_search_toolbar_title));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

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

    private void configureSwipeRefreshLayout() {
        mSearchResultSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeSearchHttpRequest();
            }
        });
    }

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
            //TODO ajouter une methode pour convertir le format de la date pour la requete
            beginDate = Utils.convertDateForQuery(intent.getStringExtra(Constants.BEGINDATE));
            mQueries.put("begin_date", beginDate);
        }
        if (!intent.getStringExtra(Constants.ENDDATE).isEmpty()) {
            //TODO ajouter une methode pour convertir le format de la date pour la requete
            endDate = Utils.convertDateForQuery(intent.getStringExtra(Constants.ENDDATE));
            mQueries.put("end_date", endDate);
        }

    }

    private void executeSearchHttpRequest() {
        getIntentExtra();
        mDisposable = NyTimesStream.streamFetchSearchArticles(mQueries)
                .subscribeWith(new DisposableObserver<Article>() {
                    @Override
                    public void onNext(Article article) {
                        if (!article.getResponse().getDocs().isEmpty()) {
                            mSearchResultSwipeLayout.setRefreshing(false);
                            mDocList.clear();
                            mDocList.addAll(article.getResponse().getDocs());
                            mArticleAdapter.notifyDataSetChanged();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                            builder.setTitle("Sorry")
                                    .setMessage("There is no news that match your search. Please, modify your search and try again")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            builder.show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("error", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
