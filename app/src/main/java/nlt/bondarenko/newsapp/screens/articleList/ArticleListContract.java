package nlt.bondarenko.newsapp.screens.articleList;

public interface ArticleListContract {

    interface ArticleListView {
        void updateMessage(String message);
    }

    interface ArticleListPresenter {
        void attach(ArticleListContract.ArticleListView view);

        void detach();

        void onClickItemListView();
    }
}
