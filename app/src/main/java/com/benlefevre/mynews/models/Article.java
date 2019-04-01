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

    public void setResponse(Response response) {
        this.response = response;
    }

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

        public void setMultimedia(List<Multimedium> multimedia) {
            this.multimedia = multimedia;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSection() {
            return section;
        }

        public String getSubsection() {
            return subsection;
        }

        public void setSubsection(String subsection) {
            this.subsection = subsection;
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

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSubType() {
            return subType;
        }

        public void setSubType(String subType) {
            this.subType = subType;
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

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public List<Multimedium> getMultimedia() {
            return multimedia;
        }

        public void setMultimedia(List<Multimedium> multimedia) {
            this.multimedia = multimedia;
        }

        public Headline getHeadline() {
            return headline;
        }

        public void setHeadline(Headline headline) {
            this.headline = headline;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
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

        public void setMain(String main) {
            this.main = main;
        }
    }

    public class Legacy {

        @SerializedName("xlarge")
        @Expose
        private String xlarge;
        @SerializedName("xlargewidth")
        @Expose
        private Integer xlargewidth;
        @SerializedName("xlargeheight")
        @Expose
        private Integer xlargeheight;
        @SerializedName("wide")
        @Expose
        private String wide;
        @SerializedName("widewidth")
        @Expose
        private Integer widewidth;
        @SerializedName("wideheight")
        @Expose
        private Integer wideheight;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("thumbnailwidth")
        @Expose
        private Integer thumbnailwidth;
        @SerializedName("thumbnailheight")
        @Expose
        private Integer thumbnailheight;

        public String getXlarge() {
            return xlarge;
        }

        public void setXlarge(String xlarge) {
            this.xlarge = xlarge;
        }

        public Integer getXlargewidth() {
            return xlargewidth;
        }

        public void setXlargewidth(Integer xlargewidth) {
            this.xlargewidth = xlargewidth;
        }

        public Integer getXlargeheight() {
            return xlargeheight;
        }

        public void setXlargeheight(Integer xlargeheight) {
            this.xlargeheight = xlargeheight;
        }

        public String getWide() {
            return wide;
        }

        public void setWide(String wide) {
            this.wide = wide;
        }

        public Integer getWidewidth() {
            return widewidth;
        }

        public void setWidewidth(Integer widewidth) {
            this.widewidth = widewidth;
        }

        public Integer getWideheight() {
            return wideheight;
        }

        public void setWideheight(Integer wideheight) {
            this.wideheight = wideheight;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Integer getThumbnailwidth() {
            return thumbnailwidth;
        }

        public void setThumbnailwidth(Integer thumbnailwidth) {
            this.thumbnailwidth = thumbnailwidth;
        }

        public Integer getThumbnailheight() {
            return thumbnailheight;
        }

        public void setThumbnailheight(Integer thumbnailheight) {
            this.thumbnailheight = thumbnailheight;
        }
    }
}
