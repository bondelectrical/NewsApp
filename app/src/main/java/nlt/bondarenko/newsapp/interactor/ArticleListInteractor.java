package nlt.bondarenko.newsapp.interactor;

import android.content.Context;

import java.io.IOException;

import nlt.bondarenko.newsapp.util.newsApi.models.Article;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;

public interface ArticleListInteractor {

    ArticleResponse getArticleListNews() throws IOException;

    void setNewsBookMarks(Context context, Article article);

    ArticleResponse getArticleListSearchNews(String search) throws IOException;
}
