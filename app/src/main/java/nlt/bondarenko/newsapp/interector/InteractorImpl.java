package nlt.bondarenko.newsapp.interector;

import java.io.IOException;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;
import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;

public class InteractorImpl implements Interactor {

    private Repository repository = RepositoryImpl.getRepositoryImpl();

    @Override
    public SourceResponse getSourceListNews() throws IOException {
        return repository.getSourceList();
    }

    @Override
    public ArticleResponse getArticleListNews() throws IOException {
        return repository.getArticleSourceList();
    }


}
