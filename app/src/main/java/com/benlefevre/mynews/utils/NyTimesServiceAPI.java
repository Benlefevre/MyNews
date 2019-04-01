package com.benlefevre.mynews.utils;

import com.benlefevre.mynews.models.Article;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface NyTimesServiceAPI {

    String api_key = "ddue1oi0I76xT3S5PFG7UFloy1BVXKzb";

    String URL = "https://api.nytimes.com/svc/";

//    Configures Retrofit with the base url, the Gson converter and the RxJava call Adapter
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

//    Make the http request to fetch results on TopStories API
    @GET("topstories/v2/{subject}.json")
    Observable<Article> getTopStoriesArticles(@Path("subject") String subject, @Query("api-key") String api_key);

//    Make the http request to fetch results on MostPopular API
    @GET("mostpopular/v2/viewed/7.json")
    Observable<Article> getMostPopularArticles(@Query("api-key") String api_key);

//    Make the http request to fetch results on Search API
    @GET("search/v2/articlesearch.json")
    Observable<Article> getSearchArticles(@QueryMap Map<String, String> map, @Query("api-key") String api_key);
}
