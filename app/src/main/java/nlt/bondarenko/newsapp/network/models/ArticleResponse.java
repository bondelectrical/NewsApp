package nlt.bondarenko.newsapp.network.models;

import java.util.List;

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
