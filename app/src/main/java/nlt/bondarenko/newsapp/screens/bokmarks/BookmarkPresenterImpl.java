package nlt.bondarenko.newsapp.screens.bokmarks;

import android.os.Handler;
import android.os.Looper;

import java.util.List;

import nlt.bondarenko.newsapp.interactor.BookmarkInreractor;
import nlt.bondarenko.newsapp.interactor.BookmarkInreractorImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public class BookmarkPresenterImpl implements BookmarkContract.BookmarkPresenter {

    private final BookmarkInreractor bookmarkInreractor;
    private final Handler handler;
    private BookmarkContract.BookmarkView view;

    public BookmarkPresenterImpl() {
        bookmarkInreractor = new BookmarkInreractorImpl();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void attach(BookmarkContract.BookmarkView view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void getBookmarkList() {

        Thread thread = new Thread(() -> {
            List<ArticleBookMarksEntity> newsBookMarks = bookmarkInreractor.getArticleBookMarks();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (view != null) view.updateBookMarksList(newsBookMarks);
                }
            });
//            handler.post(() -> view.updateBookMarksList(newsBookMarks));
        });
        thread.start();

    }

    @Override
    public void deleteBookmarkItem(ArticleBookMarksEntity article) {

        Thread thread = new Thread(() -> {
            bookmarkInreractor.deleteArticleBookMarks(article);
            getBookmarkList();
        });
        thread.start();

    }

    @Override
    public void shareBookmarkArticle(ArticleBookMarksEntity article) {
        view.shareArticleBookMarks(article);
    }

    @Override
    public void showArticleWebView(String url) {
        view.showArticle(url);
    }
}
