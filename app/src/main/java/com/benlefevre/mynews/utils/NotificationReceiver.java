package com.benlefevre.mynews.utils;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.models.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.benlefevre.mynews.utils.Constants.CHANNEL_ID;

public class NotificationReceiver extends BroadcastReceiver {

    private Intent mIntent;
    private int nbNews;
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;
        executeSearchHttpRequest();

    }

    /**
     * Gets the IntentExtra to build the needed map to fetch result to Search API.
     * Subscribes to the Observable returned by NyTimesStream method and send a notification to user if
     * there is results matched user's queries.
     */
    private void executeSearchHttpRequest() {
        String query = mIntent.getStringExtra(Constants.QUERY);
        String filterQuery = mIntent.getStringExtra(Constants.FILTERQUERY);
        List<Article.Doc> docList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("q", query);
        map.put("fq", filterQuery);
        map.put("sort","newest");
        Disposable disposable = NyTimesStream.streamFetchSearchArticles(map)
                .subscribeWith(new DisposableObserver<Article>() {
                    @Override
                    public void onNext(Article article) {
                        docList.addAll(article.getResponse().getDocs());
                        nbNews = docList.size();
                        if (nbNews != 0) {
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setContentTitle(mContext.getString(R.string.have_a_good_news))
                                    .setContentText(nbNews + " articles are available")
                                    .setSmallIcon(R.drawable.ic_stat_name);
                            Notification notification = builder.build();
                            NotificationManagerCompat.from(mContext).notify(0, notification);
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
}
