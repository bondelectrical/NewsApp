package nlt.bondarenko.newsapp.screens.sourceList;

import java.util.List;

import nlt.bondarenko.newsapp.data.SourceListItem;
import nlt.bondarenko.newsapp.util.newsApi.models.Source;

public interface SourceListContract {

    interface SourceListView {

        void updateSourceList(List<Source> sourceList);

        void showToast(String sourceName);
    }

    interface SourceListPresenter {

        void attach(SourceListView view);

        void detach();

        void onClickItemSourceList(SourceListItem sourceListItem, int position);

        void getSourceList();

    }
}
