package nlt.bondarenko.newsapp.interactor;

import java.util.List;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public class BookmarkInreractorImpl implements BookmarkInreractor {

    private final Repository repository = RepositoryImpl.getRepositoryImpl();


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
