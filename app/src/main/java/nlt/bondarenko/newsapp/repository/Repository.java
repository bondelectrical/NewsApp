package nlt.bondarenko.newsapp.repository;

import java.util.List;

import nlt.bondarenko.newsapp.data.SourceListItem;

public interface Repository {
    String getMessage();

    List<SourceListItem> getSourceList();
}
