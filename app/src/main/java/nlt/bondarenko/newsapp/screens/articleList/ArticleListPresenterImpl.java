package nlt.bondarenko.newsapp.screens.articleList;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.List;

import nlt.bondarenko.newsapp.interector.InteractorArticle;
import nlt.bondarenko.newsapp.interector.InteractorArticleImpl;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleListPresenterImpl implements ArticleListContract.ArticleListPresenter {

    private InteractorArticle interactor = new InteractorArticleImpl();
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
