package nlt.bondarenko.newsapp.interector;

import java.util.List;

import nlt.bondarenko.newsapp.data.SourceListItem;

public interface Interactor {

    String getMessage();

    List<SourceListItem> getSourceListNews();
}
