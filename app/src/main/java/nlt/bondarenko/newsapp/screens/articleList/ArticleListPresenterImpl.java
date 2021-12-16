package nlt.bondarenko.newsapp.screens.articleList;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.List;

import nlt.bondarenko.newsapp.interector.Interactor;
import nlt.bondarenko.newsapp.interector.InteractorImpl;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleListPresenterImpl implements ArticleListContract.ArticleListPresenter {

    private Interactor interactor = new InteractorImpl();
    private ArticleListContract.ArticleListView view;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void attach(ArticleListContract.ArticleListView view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void getArticleList() {
        Thread thread = new Thread(() -> {
            try {
                List<Article> articleList = interactor.getArticleListNews().getArticles();
                handler.post(() -> view.updateArticleList(articleList));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        thread.start();

    }
}
