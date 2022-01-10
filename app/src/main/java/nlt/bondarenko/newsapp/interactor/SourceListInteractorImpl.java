package nlt.bondarenko.newsapp.interactor;

import javax.inject.Inject;

import nlt.bondarenko.newsapp.NewsApp;
import nlt.bondarenko.newsapp.network.models.SourceResponse;
import nlt.bondarenko.newsapp.repository.Repository;

public class SourceListInteractorImpl implements SourceListInteractor {

    //    private final Repository repository = RepositoryImpl.getRepositoryImpl();
    @Inject
    Repository repository;

    public SourceListInteractorImpl() {
        NewsApp.repositoryComponent().inject(this);
    }

    @Override
    public SourceResponse getSourceList() {
        return repository.getSourceList();
    }
}
