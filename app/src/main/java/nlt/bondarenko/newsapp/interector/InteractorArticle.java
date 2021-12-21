package nlt.bondarenko.newsapp.interector;

import java.io.IOException;

import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;

public interface InteractorArticle {

    ArticleResponse getArticleListNews() throws IOException;
}
