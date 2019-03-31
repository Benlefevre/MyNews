package com.benlefevre.mynews.controllers.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.benlefevre.mynews.models.ArticleMost;
import com.benlefevre.mynews.models.ArticleSearch;
import com.benlefevre.mynews.models.ArticleTop;
import com.benlefevre.mynews.utils.NyTimesStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class NyStreamTest {

    // Context of the app under test.
    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

//    Test if the device has a network access
    @Test
    public void internetConnectivityTest() {
        ConnectivityManager cm =
                (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        Assert.assertTrue(isConnected);
    }

    @Test
    public void streamFetchTopStoriesArticle(){
        String query = "home";
        Observable<ArticleTop> articleTopObservable = NyTimesStream.streamFetchTopStoriesArticles(query);
        TestObserver<ArticleTop> articleTopTestObserver = new TestObserver<>();

        articleTopObservable.subscribeWith(articleTopTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        assertNotEquals(0,articleTopTestObserver.values().size());

        ArticleTop articleTop = articleTopTestObserver.values().get(0);
        ArticleTop.Result result = articleTop.getResults().get(0);

        assertEquals("OK",articleTop.getStatus());
        assertEquals(query,articleTop.getSection());

        assertNotNull(result.getTitle());
        assertNotNull(result.getUrl());
        assertNotNull(result.getSection());
        assertNotNull(result.getSubsection());
        assertNotNull(result.getPublishedDate());

    }

    @Test
    public void streamFetchMostPopularArticles(){
        Observable<ArticleMost> articleMostObservable = NyTimesStream.streamFetchMostPopularArticles();
        TestObserver<ArticleMost> articleMostTestObserver = new TestObserver<>();

        articleMostObservable.subscribeWith(articleMostTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        assertNotEquals(0,articleMostTestObserver.values().size());

        ArticleMost articleMost = articleMostTestObserver.values().get(0);
        ArticleMost.Result result = articleMost.getResults().get(0);

        assertEquals("OK",articleMost.getStatus());

        assertNotNull(result.getTitle());
        assertNotNull(result.getUrl());
        assertNotNull(result.getSection());
        assertNotNull(result.getPublishedDate());
    }

    @Test
    public void streamFetchSearchArticles(){
        Map<String, String> map = new HashMap<>();
        map.put("q","nba");
        map.put("fq", "( \"sport\")");
        map.put("beginDate","20180101");
        map.put("endDate","20190301");
        Observable<ArticleSearch> articleSearchObservable = NyTimesStream.streamFetchSearchArticles(map);
        TestObserver<ArticleSearch> articleSearchTestObserver = new TestObserver<>();

        articleSearchObservable.subscribeWith(articleSearchTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        assertNotEquals(0,articleSearchTestObserver.values().size());

        ArticleSearch articleSearch = articleSearchTestObserver.values().get(0);
        ArticleSearch.Doc doc = articleSearch.getResponse().getDocs().get(0);

        assertEquals("OK",articleSearch.getStatus());

        assertNotNull(doc.getPubDate());
        assertNotNull(doc.getHeadline().getMain());
        assertNotNull(doc.getSectionName());
        assertNotNull(doc.getWebUrl());
    }
}
