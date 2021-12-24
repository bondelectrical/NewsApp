package nlt.bondarenko.newsapp.interactor;

import android.content.Context;

import java.io.IOException;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;

public class ArticleListInteractorImpl implements ArticleListInteractor {

    private final Repository repository = RepositoryImpl.getRepositoryImpl();

    @Override
    public ArticleResponse getArticleListNews() throws IOException {
        return repository.getArticleSourceList();
    }

    @Override
    public void setNewsBookMarks(Context context, Article article) {
        NewsBookMarksEntity newsBookMarksEntity = new NewsBookMarksEntity(article.getName(),
                article.getTitle(), article.getDescription(),
                article.getUrl(), article.getUrlToImage());
        repository.setNewsBookMarksEntity(context, newsBookMarksEntity);
    }

    @Override
    public ArticleResponse getArticleListSearchNews(String search) throws IOException {
        return repository.getArticleResponseSearchList(search);
    }

}
