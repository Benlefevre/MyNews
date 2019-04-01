package com.benlefevre.mynews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.models.Article;
import com.benlefevre.mynews.views.ArticleViewHolder;
import com.bumptech.glide.RequestManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article.Result> mResultList;
    private List<Article.Doc> mDocList;
    private RequestManager mRequestManager;
    private int mTypeOfArticle;

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
        Context context =parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_item,parent,false);
        return new ArticleViewHolder(view,mTypeOfArticle);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        if(mTypeOfArticle!=0)
            holder.updateUi(mResultList.get(position),mRequestManager);
        else
            holder.updateUiForResearch(mDocList.get(position),mRequestManager);
    }

    @Override
    public int getItemCount() {
        if(mTypeOfArticle!=0)
            return mResultList.size();
        else
            return mDocList.size();
    }
}
