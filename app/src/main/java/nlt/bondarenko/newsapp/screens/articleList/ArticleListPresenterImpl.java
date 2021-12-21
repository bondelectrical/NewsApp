package nlt.bondarenko.newsapp.screens.articleList;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import nlt.bondarenko.newsapp.interactor.ArticleInteractor;
import nlt.bondarenko.newsapp.interactor.ArticleInteractorImpl;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleListPresenterImpl implements ArticleListContract.ArticleListPresenter {

    private final ArticleInteractor interactor;
    private ArticleListContract.ArticleListView view;
    private Handler handler;
    private Context context;

    public ArticleListPresenterImpl(Context context) {
        this.context = context;
        interactor = new ArticleInteractorImpl();
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
        Toast.makeText(context, " " + news.getName(), Toast.LENGTH_SHORT).show();
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

}
