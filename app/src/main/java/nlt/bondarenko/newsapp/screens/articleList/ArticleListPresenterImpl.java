package nlt.bondarenko.newsapp.screens.articleList;

import nlt.bondarenko.newsapp.interector.Interactor;
import nlt.bondarenko.newsapp.interector.InteractorImpl;

public class ArticleListPresenterImpl implements ArticleListContract.ArticleListPresenter {

    private Interactor interactor = new InteractorImpl();
    private ArticleListContract.ArticleListView view;


    @Override
    public void onClickItemListView() {
        view.updateMessage(interactor.getMessage());
    }

    @Override
    public void attach(ArticleListContract.ArticleListView view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
