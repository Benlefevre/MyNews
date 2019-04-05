package com.benlefevre.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public String getStatus() {
        return status;
    }

    public String getSection() {
        return section;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Result {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("section")
        @Expose
        private String section;
        @SerializedName("subsection")
        @Expose
        private String subsection;
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

        @SerializedName("multimedia")
        @Expose
        private List<Multimedium> multimedia = null;

        @SerializedName("uri")
        @Expose
        private String uri;

        public List<Multimedium> getMultimedia() {
            return multimedia;
        }

        public String getUrl() {
            return url;
        }

        public String getSection() {
            return section;
        }

        public String getSubsection() {
            return subsection;
        }

        public String getTitle() {
            return title;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public String getSource() {
            return source;
        }

        public List<Medium> getMedia() {
            return media;
        }

    }

    public class MediaMetadatum {

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }

    }

    public class Medium {

        @SerializedName("media-metadata")
        @Expose
        private List<MediaMetadatum> mediaMetadata = null;

        public List<MediaMetadatum> getMediaMetadata() {
            return mediaMetadata;
        }

    }

    public class Multimedium {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("subType")
        @Expose
        private String subType;

        public String getUrl() {
            return url;
        }


        public String getSubType() {
            return subType;
        }

    }

    public class Response {

        @SerializedName("docs")
        @Expose
        private List<Doc> docs = null;

        public List<Doc> getDocs() {
            return docs;
        }

        public void setDocs(List<Doc> docs) {
            this.docs = docs;
        }
    }

    public class Doc {

        @SerializedName("web_url")
        @Expose
        private String webUrl;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("multimedia")
        @Expose
        private List<Multimedium> multimedia = null;
        @SerializedName("headline")
        @Expose
        private Headline headline;
        @SerializedName("pub_date")
        @Expose
        private String pubDate;
        @SerializedName("section_name")
        @Expose
        private String sectionName;
        @SerializedName("_id")
        @Expose
        private String id;

        public String getWebUrl() {
            return webUrl;
        }

        public String getSource() {
            return source;
        }

        public List<Multimedium> getMultimedia() {
            return multimedia;
        }

        public Headline getHeadline() {
            return headline;
        }

        public String getPubDate() {
            return pubDate;
        }

        public String getSectionName() {
            return sectionName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public class Headline {

        @SerializedName("main")
        @Expose
        private String main;

        public String getMain() {
            return main;
        }
    }

}
