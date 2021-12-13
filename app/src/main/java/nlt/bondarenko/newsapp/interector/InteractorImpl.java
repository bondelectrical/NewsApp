package nlt.bondarenko.newsapp.interector;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;

public class InteractorImpl implements Interactor {

    private Repository repository = new RepositoryImpl();

    @Override
    public String getMessage() {
        return repository.getMessage() + " Alexey";
    }
}
