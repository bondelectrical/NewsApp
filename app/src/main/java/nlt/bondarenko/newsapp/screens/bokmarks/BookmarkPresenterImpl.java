package nlt.bondarenko.newsapp.screens.bokmarks;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.fragment.app.FragmentManager;

import java.util.List;

import nlt.bondarenko.newsapp.interactor.BookmarkInreractor;
import nlt.bondarenko.newsapp.interactor.BookmarkInreractorImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;

public class BookmarkPresenterImpl implements BookmarkContract.BookmarkPresenter {

    private final BookmarkInreractor bookmarkInreractor;
    private final Handler handler;
    private BookmarkContract.BookmarkView view;
    private Context context;

    public BookmarkPresenterImpl(Context context) {
        this.context = context;
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
            List<NewsBookMarksEntity> newsBookMarks = bookmarkInreractor.getNewsBookMarks(context);
            handler.post(() -> view.updateBookMarksList(newsBookMarks));
        });
        thread.start();

    }

    @Override
    public void deleteBookmarkItem(NewsBookMarksEntity news) {
        Thread thread = new Thread(() -> {
            bookmarkInreractor.deleteNewsBookMarks(context, news);
            getBookmarkList();
        });
        thread.start();

    }

    @Override
    public void shareBookmarkArticle(NewsBookMarksEntity news) {
        bookmarkInreractor.shareNewsBookMarks(context, news);
    }

    @Override
    public void showArticleWebView(FragmentManager fragmentManager, String url) {
        bookmarkInreractor.showArticleNews(fragmentManager, url);
    }
}
