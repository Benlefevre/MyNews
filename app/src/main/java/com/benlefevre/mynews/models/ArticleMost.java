package com.benlefevre.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleMost {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


    public class MediaMetadatum {

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class Medium {

        @SerializedName("media-metadata")
        @Expose
        private List<MediaMetadatum> mediaMetadata = null;

        public List<MediaMetadatum> getMediaMetadata() {
            return mediaMetadata;
        }

        public void setMediaMetadata(List<MediaMetadatum> mediaMetadata) {
            this.mediaMetadata = mediaMetadata;
        }

    }

    public class Result {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("section")
        @Expose
        private String section;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("published_date")
        @Expose
        private String publishedDate;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("id")
        @Expose
        private long id;
        @SerializedName("media")
        @Expose
        private List<Medium> media = null;
        @SerializedName("uri")
        @Expose
        private String uri;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSection() {
            return section;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public long getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }


        public List<Medium> getMedia() {
            return media;
        }

        public void setMedia(List<Medium> media) {
            this.media = media;
        }

    }
}
