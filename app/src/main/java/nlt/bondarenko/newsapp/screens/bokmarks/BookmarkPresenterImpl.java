package nlt.bondarenko.newsapp.screens.bokmarks;

import android.content.Context;

import java.util.List;

import nlt.bondarenko.newsapp.interector.InreractorBookmark;
import nlt.bondarenko.newsapp.interector.InreractorBookmarkImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;

public class BookmarkPresenterImpl implements BookmarkContract.BookmarkPresenter {

    private InreractorBookmark inreractorBookmark = new InreractorBookmarkImpl();

    private BookmarkContract.BookmarkView view;
    private Context context;

    public BookmarkPresenterImpl(Context context) {
        this.context = context;
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


        List<NewsBookMarksEntity> newsBookMarks = inreractorBookmark.getNewsBookMarks(context);

    }

    @Override
    public void deleteBookmarkItem(long id) {

    }
}
