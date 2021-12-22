package nlt.bondarenko.newsapp.screens.bokmarks;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

import nlt.bondarenko.newsapp.interactor.InreractorBookmark;
import nlt.bondarenko.newsapp.interactor.InreractorBookmarkImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;

public class BookmarkPresenterImpl implements BookmarkContract.BookmarkPresenter {

    private final InreractorBookmark inreractorBookmark;
    private final Handler handler;
    private BookmarkContract.BookmarkView view;
    private Context context;

    public BookmarkPresenterImpl(Context context) {
        this.context = context;
        inreractorBookmark = new InreractorBookmarkImpl();
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
            List<NewsBookMarksEntity> newsBookMarks = inreractorBookmark.getNewsBookMarks(context);
            handler.post(() -> view.updateBookMarksList(newsBookMarks));
        });
        thread.start();

    }

    @Override
    public void deleteBookmarkItem(NewsBookMarksEntity news) {
        Thread thread = new Thread(() -> {
            inreractorBookmark.deleteNewsBookMarks(context, news);
            getBookmarkList();
        });
        thread.start();

    }

    @Override
    public void shareBookmarkArticle(NewsBookMarksEntity news) {
        inreractorBookmark.shareNewsBookMarks(context, news);
    }
}
