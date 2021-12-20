package nlt.bondarenko.newsapp.screens.bokmarks;

public class BookmarkPresenterImpl implements BookmarkContract.BookmarkPresenter {

    private BookmarkContract.BookmarkView view;


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

    }

    @Override
    public void deleteBookmarkItem(long id) {

    }
}
