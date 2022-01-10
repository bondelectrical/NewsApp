package nlt.bondarenko.newsapp.interactor;


import javax.inject.Inject;

import nlt.bondarenko.newsapp.NewsApp;
import nlt.bondarenko.newsapp.network.models.Article;
import nlt.bondarenko.newsapp.network.models.ArticleResponse;
import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;


public class ArticleListInteractorImpl implements ArticleListInteractor {

//    private final Repository repository = RepositoryImpl.getRepositoryImpl();

    @Inject
    Repository repository;

    public ArticleListInteractorImpl() {
        NewsApp.repositoryComponent().inject(this);
    }

    @Override
    public ArticleResponse getArticleList() {
        return repository.getArticleList();
    }

    @Override
    public void saveArticleBookMark(Article article) {
        ArticleBookMarksEntity articleBookMarksEntity = new ArticleBookMarksEntity(article.getName(),
                article.getTitle(), article.getDescription(),
                article.getUrl(), article.getUrlToImage());
        repository.saveArticleBookMarks(articleBookMarksEntity);
    }

    @Override
    public ArticleResponse getSearchArticleList(String search) {
        return repository.getSearchArticleResponseList(search);
    }

}
