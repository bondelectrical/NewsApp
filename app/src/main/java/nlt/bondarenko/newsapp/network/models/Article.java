package nlt.bondarenko.newsapp.network.models;

import com.google.gson.annotations.SerializedName;

import nlt.bondarenko.newsapp.data.News;

public class Article implements News {

    @SerializedName("source")
    private ArticleSource articleSource;

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("content")
    private String content;


    @Override
    public String getName() {
        return articleSource.getName();
    }

    public ArticleSource getArticleSource() {
        return articleSource;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }
}
