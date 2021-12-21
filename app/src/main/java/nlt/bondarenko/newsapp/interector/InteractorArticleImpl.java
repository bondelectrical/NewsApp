package nlt.bondarenko.newsapp.interector;

import java.io.IOException;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;

public class InteractorArticleImpl implements InteractorArticle {

    private Repository repository = RepositoryImpl.getRepositoryImpl();

    @Override
    public ArticleResponse getArticleListNews() throws IOException {
        return repository.getArticleSourceList();
    }

}
