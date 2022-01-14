package nlt.bondarenko.newsapp.screens.bokmarks;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nlt.bondarenko.newsapp.interactor.BookmarkInreractor;
import nlt.bondarenko.newsapp.interactor.BookmarkInreractorImpl;
import nlt.bondarenko.newsapp.network.models.Article;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public class BookmarkListViewModel extends ViewModel {
    public LiveData<List<ArticleBookMarksEntity>> livedata;
    private MutableLiveData<List<ArticleBookMarksEntity>> articleBookmark;
    private BookmarkInreractor interactor = new BookmarkInreractorImpl();


    public BookmarkListViewModel() {
        articleBookmark = new MutableLiveData<>();
        livedata = articleBookmark;
        loadArticleBookmark();
    }

    private void loadArticleBookmark() {
        Handler handler = new Handler(Looper.getMainLooper());
        Thread thread = new Thread(() -> {
            List<ArticleBookMarksEntity> newsBookMarks = interactor.getArticleBookMarks();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    articleBookmark.setValue(newsBookMarks);
                }
            });
        });
        thread.start();
    }

    public void deleteArticleBookmark(ArticleBookMarksEntity article) {
        Thread thread = new Thread(() -> {
            interactor.deleteArticleBookMarks(article);
        });
        thread.start();
        loadArticleBookmark();
    }

    public void addArticleBookmark(Article article) {
        ArticleBookMarksEntity articleBookMarksEntity = new ArticleBookMarksEntity(article.getName(),
                article.getTitle(), article.getDescription(),
                article.getUrl(), article.getUrlToImage());
        new Thread(new Runnable() {
            @Override
            public void run() {
                interactor.addArticleBookMarks(articleBookMarksEntity);

            }
        }).start();
        loadArticleBookmark();
    }
}
