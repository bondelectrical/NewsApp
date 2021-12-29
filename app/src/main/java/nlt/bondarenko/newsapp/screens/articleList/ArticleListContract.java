package nlt.bondarenko.newsapp.screens.articleList;

import java.util.List;

import nlt.bondarenko.newsapp.network.models.Article;

public interface ArticleListContract {

    interface ArticleListView {

        void updateArticleList(List<Article> articleList);

        void shareArticle(Article news);

        void showArticle(String url);
    }

    interface ArticleListPresenter {
        void attach(ArticleListContract.ArticleListView view);

        void detach();

        void setArticleDataBase(Article news);

        void getArticleList();

        void shareArticleNews(Article news);

        void showArticleWebView(String url);

        void getSearchArticleList(String search);

    }
}
