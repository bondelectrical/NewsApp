package nlt.bondarenko.newsapp.interector;

import java.util.List;

import nlt.bondarenko.newsapp.data.SourceListItem;
import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;

public class InteractorImpl implements Interactor {

    private Repository repository = new RepositoryImpl();

    @Override
    public String getMessage() {
        return repository.getMessage() + " Alexey";
    }

    @Override
    public List<SourceListItem> getSourceListNews() {
        return repository.getSourceList();
    }


}
