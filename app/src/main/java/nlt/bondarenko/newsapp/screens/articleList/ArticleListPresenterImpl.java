package nlt.bondarenko.newsapp.screens.articleList;

import android.os.Handler;
import android.os.Looper;

import java.util.List;

import nlt.bondarenko.newsapp.interactor.ArticleListInteractor;
import nlt.bondarenko.newsapp.interactor.ArticleListInteractorImpl;
import nlt.bondarenko.newsapp.network.models.Article;

public class ArticleListPresenterImpl implements ArticleListContract.ArticleListPresenter {

    private final ArticleListInteractor interactor;
    private ArticleListContract.ArticleListView view;
    private Handler handler;

    public ArticleListPresenterImpl() {
        interactor = new ArticleListInteractorImpl();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void attach(ArticleListContract.ArticleListView view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void setArticleDataBase(Article news) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                interactor.saveArticleBookMark(news);

            }
        }).start();

    }


    @Override
    public void getArticleList() {
        Thread thread = new Thread(() -> {
            List<Article> articleList = interactor.getArticleList().getArticles();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (view != null) view.updateArticleList(articleList);
                }
            });
//            handler.post(() -> view.updateArticleList(articleList));
        });
        thread.start();
    }


    @Override
    public void shareArticleNews(Article news) {
        view.shareArticle(news);
    }

    @Override
    public void showArticleWebView(String url) {
        view.showArticle(url);
    }

    @Override
    public void getSearchArticleList(String search) {
        Thread thread = new Thread(() -> {
            List<Article> articleList = interactor.getSearchArticleList(search).getArticles();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (view != null) view.updateArticleList(articleList);
                }
            });
//            handler.post(() -> view.updateArticleList(articleList));
        });
        thread.start();
    }

}
