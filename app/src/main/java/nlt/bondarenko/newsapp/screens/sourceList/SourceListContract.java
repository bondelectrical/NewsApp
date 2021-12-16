package nlt.bondarenko.newsapp.screens.sourceList;

import java.util.List;

import nlt.bondarenko.newsapp.util.newsApi.models.Source;

public interface SourceListContract {

    interface SourceListView {

        void updateSourceList(List<Source> sourceList);

        void showSourceUrl(String sourceUrl);
    }

    interface SourceListPresenter {

        void attach(SourceListView view);

        void detach();

        void onClickItemSourceList(Source sourceListItem, int position);

        void getSourceList();

    }
}
