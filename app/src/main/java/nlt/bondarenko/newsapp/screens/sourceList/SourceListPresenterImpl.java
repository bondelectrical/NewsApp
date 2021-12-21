package nlt.bondarenko.newsapp.screens.sourceList;


import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.List;

import nlt.bondarenko.newsapp.interactor.SourceListInteractor;
import nlt.bondarenko.newsapp.interactor.SourceListInteractorImpl;
import nlt.bondarenko.newsapp.util.newsApi.models.Source;

public class SourceListPresenterImpl implements SourceListContract.SourceListPresenter {

    private SourceListContract.SourceListView view;
    private SourceListInteractor interactor = new SourceListInteractorImpl();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void attach(SourceListContract.SourceListView view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void onClickItemSourceList(Source sourceListItem, int position) {
        String address = sourceListItem.getUrl();
        view.showSourceUrl(address);

    }

    @Override
    public void getSourceList() {
        Thread thread = new Thread(() -> {
            try {
                List<Source> sources = interactor.getSourceListNews().getSources();
                handler.post(() -> view.updateSourceList(sources));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        thread.start();
    }
}
