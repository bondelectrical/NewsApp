package nlt.bondarenko.newsapp.screens.articleList;

import java.util.List;

import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public interface ArticleListContract {

    interface ArticleListView {

        void updateArticleList(List<Article> articleList);
    }

    interface ArticleListPresenter {
        void attach(ArticleListContract.ArticleListView view);

        void detach();

        void getArticleList();

    }
}
