package nlt.bondarenko.newsapp.screens.articleList;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public interface ArticleListContract {

    interface ArticleListView {

        void updateArticleList(List<Article> articleList);
    }

    interface ArticleListPresenter {
        void attach(ArticleListContract.ArticleListView view);

        void detach();

        void setArticleDataBase(Article news);

        void getArticleList();

        void shareArticleNews(Article news);

        void showArticleWebView(FragmentManager fragmentManager, String url, BottomNavigationView bottom);

        void getArticleListSearch(String search);

    }
}
