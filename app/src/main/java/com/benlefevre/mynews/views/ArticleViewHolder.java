package com.benlefevre.mynews.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.models.Article;
import com.benlefevre.mynews.utils.Utils;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

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

    public ArticleViewHolder(@NonNull View itemView, int typeOfArticle) {
        super(itemView);
        mTypeOfArticle = typeOfArticle;
        ButterKnife.bind(this, itemView);
    }


    public void updateUi(Article.Result result, RequestManager requestManager) {
        updateSection(result);
        mTitle.setText(Utils.convertTitleForDisplay(result.getTitle()));
        mDate.setText(Utils.convertDateForDisplay(result.getPublishedDate()));
        updateImageView(result, requestManager);
    }

    public void updateUiForResearch(Article.Doc doc, RequestManager requestManager) {
        mSection.setText(doc.getSectionName());
        mTitle.setText(Utils.convertTitleForDisplay(doc.getHeadline().getMain()));
        mDate.setText(Utils.convertDateForDisplay(doc.getPubDate()));
        List<Article.Multimedium> multimedia = new ArrayList<>();
        multimedia.addAll(doc.getMultimedia());
        String url = null;
        if (multimedia.size() != 0) {
            for (Article.Multimedium multimedium : multimedia) {
                if (multimedium.getSubType().equals("Thumbnail"))
                    url = "https://static01.nyt.com/" + multimedium.getUrl();
            }
            requestManager.load(url).into(mImageView);
        }else
            mImageView.setImageResource(R.drawable.ic_broken_image);
    }

    /**
     * Updates the TextView's text according to result contains or not a Subsection
     * @param result The http request's result
     */
    private void updateSection(Article.Result result) {
        if (result.getSubsection() != null && !result.getSubsection().equals("")) {
            String section = result.getSection() + " > " + result.getSubsection();
            mSection.setText(section);
        } else
            mSection.setText(result.getSection());
    }

    /**
     * Loads a picture in the RecyclerView's ImageView according to the kind of Article and if
     * the http request's result contains pictures.
     * @param result the http request's result
     * @param requestManager Google Glide here
     */
    private void updateImageView(Article.Result result, RequestManager requestManager) {
        if (mTypeOfArticle == 1) {
            if (result.getMultimedia().size() != 0)
                requestManager.load(result.getMultimedia().get(0).getUrl()).into(mImageView);
            else
                mImageView.setImageResource(R.drawable.ic_broken_image);
        } else {
            if (result.getMedia().get(0).getMediaMetadata().size() != 0)
                requestManager.load(result.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(mImageView);
            else
                mImageView.setImageResource(R.drawable.ic_broken_image);
        }
    }
}
