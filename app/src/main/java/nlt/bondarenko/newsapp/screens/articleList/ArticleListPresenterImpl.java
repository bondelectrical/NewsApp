package nlt.bondarenko.newsapp.screens.articleList;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;

import nlt.bondarenko.newsapp.interactor.ArticleListInteractor;
import nlt.bondarenko.newsapp.interactor.ArticleListInteractorImpl;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleListPresenterImpl implements ArticleListContract.ArticleListPresenter {

    private final ArticleListInteractor interactor;
    private ArticleListContract.ArticleListView view;
    private Handler handler;
    private Context context;

    public ArticleListPresenterImpl(Context context) {
        this.context = context;
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
                interactor.setNewsBookMarks(context, news);

            }
        }).start();

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

    @Override
    public void shareArticleNews(Article news) {
        interactor.shareArticleNews(context, news);
    }

    @Override
    public void showArticleWebView(FragmentManager fragmentManager, String url, BottomNavigationView bottom) {
        interactor.showArticleNews(fragmentManager, url, bottom);
    }

    @Override
    public void getArticleListSearch(String search) {
        Thread thread = new Thread(() -> {
            try {
                List<Article> articleList = interactor.getArticleListSearchNews(search).getArticles();
                handler.post(() -> view.updateArticleList(articleList));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        thread.start();
    }

}
