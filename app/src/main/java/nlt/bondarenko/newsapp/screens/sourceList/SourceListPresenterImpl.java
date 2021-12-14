package nlt.bondarenko.newsapp.screens.sourceList;


import nlt.bondarenko.newsapp.data.SourceListItem;
import nlt.bondarenko.newsapp.interector.Interactor;
import nlt.bondarenko.newsapp.interector.InteractorImpl;

public class SourceListPresenterImpl implements SourceListContract.SourceListPresenter {

    private SourceListContract.SourceListView view;
    private Interactor interactor = new InteractorImpl();

    @Override
    public void attach(SourceListContract.SourceListView view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void onClickItemSourceList(SourceListItem sourceListItem, int position) {
        view.showToast(sourceListItem.getSourceNews());

    }

    @Override
    public void getSourceList() {
        view.updateSourceList(interactor.getSourceListNews());
    }
}
