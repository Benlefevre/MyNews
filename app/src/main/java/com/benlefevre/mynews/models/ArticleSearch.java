package com.benlefevre.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleSearch {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private Response response;

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

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    public class Blog {
    }


    public class Byline {

        @SerializedName("original")
        @Expose
        private Object original;
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
}
