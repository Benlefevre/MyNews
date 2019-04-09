package com.benlefevre.mynews.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.models.Article;
import com.benlefevre.mynews.utils.Constants;
import com.benlefevre.mynews.views.ArticleViewHolder;
import com.bumptech.glide.RequestManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article.Result> mResultList;
    private List<Article.Doc> mDocList;
    private RequestManager mRequestManager;
    private SharedPreferences mSharedPreferences;
    private int mTypeOfArticle;

//    We have 2 ArticleAdapter's constructor to match the Http request used.
//    If we use TopStories and MostPopular requests, we need typeOfArticle because image's paths
//    are different.
    public ArticleAdapter(List<Article.Result> resultList, RequestManager requestManager, int typeOfArticle) {
        mResultList = resultList;
        mRequestManager = requestManager;
        mTypeOfArticle = typeOfArticle;
    }

    public ArticleAdapter(List<Article.Doc> docList, RequestManager requestManager) {
        mDocList = docList;
        mRequestManager = requestManager;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
//        We fetch SharedPreferences that we need to put them in ViewHolder's constructor for change
//        the read article's background color
        mSharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ArticleViewHolder(view, mSharedPreferences, mTypeOfArticle);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
//        According to the mTypeOfArticle's value, we call holder's method to update the UI
        if (mTypeOfArticle != 0)
            holder.updateUi(mResultList.get(position), mRequestManager);
        else
            holder.updateUiForResearch(mDocList.get(position), mRequestManager);
    }

    @Override
    public int getItemCount() {
        if (mTypeOfArticle != 0)
            return mResultList.size();
        else
            return mDocList.size();
    }

    /**
     * Returns the url of the user's selected item.
     *
     * @param position the item's position in the RecyclerView
     * @return the selected item's url
     */
    public String getUrl(int position) {
        if (mTypeOfArticle != 0)
            return mResultList.get(position).getUrl();
        else
            return mDocList.get(position).getWebUrl();
    }

    /**
     * Returns the title of the user's selected item
     *
     * @param position the item's position in the RecyclerView
     * @return the selected item's title
     */
    public String getTitle(int position) {
        if (mTypeOfArticle != 0)
            return mResultList.get(position).getTitle();
        else
            return mDocList.get(position).getHeadline().getMain();
    }


}
