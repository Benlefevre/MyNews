package com.benlefevre.mynews.utils;

import com.benlefevre.mynews.models.Article;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NyTimesStream {

    private static NyTimesServiceAPI mNyTimesServiceAPI = NyTimesServiceAPI.retrofit.create(NyTimesServiceAPI.class);

    public static Observable<Article> streamFetchTopStoriesArticles(String subject) {
        return mNyTimesServiceAPI.getTopStoriesArticles(subject,NyTimesServiceAPI.api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }


    public static Observable<Article> streamFetchMostPopularArticles() {
        return mNyTimesServiceAPI.getMostPopularArticles(NyTimesServiceAPI.api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10,TimeUnit.SECONDS);
    }

    public static Observable<Article> streamFetchSearchArticles(Map<String,String> map) {
        return mNyTimesServiceAPI.getSearchArticles(map,NyTimesServiceAPI.api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10,TimeUnit.SECONDS);
    }
}
