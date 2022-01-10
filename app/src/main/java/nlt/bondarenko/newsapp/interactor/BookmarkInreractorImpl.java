package nlt.bondarenko.newsapp.interactor;

import java.util.List;

import javax.inject.Inject;

import nlt.bondarenko.newsapp.NewsApp;
import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public class BookmarkInreractorImpl implements BookmarkInreractor {

//    private final Repository repository = RepositoryImpl.getRepositoryImpl();

    @Inject
    Repository repository;

    public BookmarkInreractorImpl() {
        NewsApp.repositoryComponent().inject(this);
    }

    @Override
    public List<ArticleBookMarksEntity> getArticleBookMarks() {
        return repository.getArticleBookMarks();
    }


    @Override
    public void deleteArticleBookMarks(ArticleBookMarksEntity article) {
        repository.deleteArticleBookMarks(article);
    }

    @Override
    public void addArticleBookMarks(ArticleBookMarksEntity article) {
        repository.saveArticleBookMarks(article);
    }

}
