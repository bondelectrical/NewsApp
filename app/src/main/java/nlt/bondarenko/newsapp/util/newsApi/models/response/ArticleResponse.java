package nlt.bondarenko.newsapp.util.newsApi.models.response;

import java.util.List;

import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
