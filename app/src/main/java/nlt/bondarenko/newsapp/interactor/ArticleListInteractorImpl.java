package nlt.bondarenko.newsapp.interactor;

import nlt.bondarenko.newsapp.network.models.Article;
import nlt.bondarenko.newsapp.network.models.ArticleResponse;
import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public class ArticleListInteractorImpl implements ArticleListInteractor {

    private final Repository repository = RepositoryImpl.getRepositoryImpl();

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
