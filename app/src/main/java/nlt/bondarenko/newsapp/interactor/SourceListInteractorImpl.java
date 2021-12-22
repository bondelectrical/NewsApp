package nlt.bondarenko.newsapp.interactor;

import java.io.IOException;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;

public class SourceListInteractorImpl implements SourceListInteractor {

    private final Repository repository = RepositoryImpl.getRepositoryImpl();

    @Override
    public SourceResponse getSourceListNews() throws IOException {
        return repository.getSourceList();
    }
}
