package nlt.bondarenko.newsapp.interactor;

import nlt.bondarenko.newsapp.network.models.SourceResponse;
import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;

public class SourceListInteractorImpl implements SourceListInteractor {

    private final Repository repository = RepositoryImpl.getRepositoryImpl();

    @Override
    public SourceResponse getSourceList() {
        return repository.getSourceList();
    }
}
