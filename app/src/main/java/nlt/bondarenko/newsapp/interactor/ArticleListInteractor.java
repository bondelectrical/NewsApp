package nlt.bondarenko.newsapp.interactor;

import nlt.bondarenko.newsapp.network.models.Article;
import nlt.bondarenko.newsapp.network.models.ArticleResponse;

public interface ArticleListInteractor {

    ArticleResponse getArticleList();

    void saveArticleBookMark(Article article);

    ArticleResponse getSearchArticleList(String search);
}
