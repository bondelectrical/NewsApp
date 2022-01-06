package nlt.bondarenko.newsapp.viewmodel;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nlt.bondarenko.newsapp.interactor.ArticleListInteractor;
import nlt.bondarenko.newsapp.interactor.ArticleListInteractorImpl;
import nlt.bondarenko.newsapp.network.models.Article;

public class ArticleListViewModel extends ViewModel {
    public LiveData<List<Article>> livedata;
    private MutableLiveData<List<Article>> articleList;
    private ArticleListInteractor interactor = new ArticleListInteractorImpl();


    public ArticleListViewModel() {
        articleList = new MutableLiveData<>();
        livedata = articleList;
        loadArticleList();
    }

    public void loadArticleList() {
        Handler handler = new Handler(Looper.getMainLooper());
        Thread thread = new Thread(() -> {
            List<Article> articles = interactor.getArticleList().getArticles();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    articleList.setValue(articles);
                }
            });
        });
        thread.start();
    }

    public void getSearchArticleList(String search) {
        Handler handler = new Handler(Looper.getMainLooper());
        Thread thread = new Thread(() -> {
            List<Article> articleSearch = interactor.getSearchArticleList(search).getArticles();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    articleList.setValue(articleSearch);
                }
            });
        });
        thread.start();
    }

}
