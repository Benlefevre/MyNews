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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class NyStreamTest {

    // Context of the app under test.
    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private String beginDate = "20180101";
    private String endDate = "20190301";

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
    public void streamFetchTopStoriesArticle() {
        String query = "home";
        Observable<ArticleTop> articleTopObservable = NyTimesStream.streamFetchTopStoriesArticles(query);
        TestObserver<ArticleTop> articleTopTestObserver = new TestObserver<>();

        articleTopObservable.subscribeWith(articleTopTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        assertNotEquals(0, articleTopTestObserver.values().size());

        ArticleTop articleTop = articleTopTestObserver.values().get(0);
        ArticleTop.Result result = articleTop.getResults().get(0);

        assertEquals("OK", articleTop.getStatus());
        assertEquals(query, articleTop.getSection());

        assertNotNull(result.getTitle());
        assertNotNull(result.getUrl());
        assertNotNull(result.getSection());
        assertNotNull(result.getSubsection());
        assertNotNull(result.getPublishedDate());
    }

    @Test
    public void streamFetchMostPopularArticles() {
        Observable<ArticleMost> articleMostObservable = NyTimesStream.streamFetchMostPopularArticles();
        TestObserver<ArticleMost> articleMostTestObserver = new TestObserver<>();

        articleMostObservable.subscribeWith(articleMostTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        assertNotEquals(0, articleMostTestObserver.values().size());

        ArticleMost articleMost = articleMostTestObserver.values().get(0);
        ArticleMost.Result result = articleMost.getResults().get(0);

        assertEquals("OK", articleMost.getStatus());

        assertNotNull(result.getTitle());
        assertNotNull(result.getUrl());
        assertNotNull(result.getSection());
        assertNotNull(result.getPublishedDate());
        assertNotNull(result.getSource());
    }

    @Test
    public void streamFetchSearchArticles() {
        Map<String, String> map = new HashMap<>();
        map.put("q", "nba");
        map.put("fq", "( \"sport\")");
        map.put("beginDate", beginDate);
        map.put("endDate", endDate);
        Observable<ArticleSearch> articleSearchObservable = NyTimesStream.streamFetchSearchArticles(map);
        TestObserver<ArticleSearch> articleSearchTestObserver = new TestObserver<>();

        articleSearchObservable.subscribeWith(articleSearchTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        assertNotEquals(0, articleSearchTestObserver.values().size());

        ArticleSearch articleSearch = articleSearchTestObserver.values().get(0);
        int numDoc = articleSearch.getResponse().getDocs().size();
        List<ArticleSearch.Doc> docList = articleSearch.getResponse().getDocs();
        ArticleSearch.Doc doc = articleSearch.getResponse().getDocs().get(0);

        assertEquals("OK", articleSearch.getStatus());

        assertNotNull(doc.getPubDate());
        assertNotNull(doc.getHeadline().getMain());
        assertNotNull(doc.getSectionName());
        assertNotNull(doc.getWebUrl());
        assertNotNull(doc.getSource());

        for (ArticleSearch.Doc doc1 : docList) {
            assertTrue(compareDate(doc.getPubDate()));
        }

    }

    public boolean compareDate(String articleDate) {
        boolean result = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date1 = simpleDateFormat.parse(articleDate);
            Date dateBegin = simpleDateFormat1.parse(beginDate);
            Date dateEnd = simpleDateFormat1.parse(endDate);
            if (((date1.compareTo(dateBegin) >= 0)) && (date1.compareTo(dateEnd) <= 0))
                result = true;
            else
                result = false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
