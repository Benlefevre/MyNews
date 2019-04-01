package com.benlefevre.mynews.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.models.Article;
import com.bumptech.glide.RequestManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_view_recycler_item)
    ImageView mImageView;
    @BindView(R.id.item_section)
    TextView mSection;
    @BindView(R.id.item_title)
    TextView mTitle;
    @BindView(R.id.item_date)
    TextView mDate;


    private int mTypeOfArticle;

    public ArticleViewHolder(@NonNull View itemView,int typeOfArticle) {
        super(itemView);
        mTypeOfArticle = typeOfArticle;
        ButterKnife.bind(this,itemView);
    }


    public void updateUi(Article.Result result, RequestManager requestManager) {
    }

    public void updateUiForResearch(Article.Doc doc, RequestManager requestManager) {
    }
}
