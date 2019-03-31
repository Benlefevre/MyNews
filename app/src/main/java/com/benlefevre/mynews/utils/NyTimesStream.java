package com.benlefevre.mynews.utils;

import com.benlefevre.mynews.models.ArticleTop;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NyTimesStream {

    private static NyTimesServiceAPI mNyTimesServiceAPI = NyTimesServiceAPI.retrofit.create(NyTimesServiceAPI.class);

    public static Observable<ArticleTop> streamFetchTopStoriesArticles(String subject) {
        return mNyTimesServiceAPI.getTopStoriesArticles(subject,NyTimesServiceAPI.api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
