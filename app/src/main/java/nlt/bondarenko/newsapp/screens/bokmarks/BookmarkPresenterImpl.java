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
            try {
                List<NewsBookMarksEntity> newsBookMarks = inreractorBookmark.getNewsBookMarks(context);
                handler.post(() -> view.updateBookMarksList(newsBookMarks));
            } catch (Error e) {
                e.printStackTrace();
            }
        });
        thread.start();


//        Executor executor = new Executor() {
//            @Override
//            public void execute(Runnable command) {
//                command.run();
//            }
//        };
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    List<NewsBookMarksEntity> newsBookMarks = inreractorBookmark.getNewsBookMarks(context);
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            view.updateBookMarksList(newsBookMarks);
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

    }

    @Override
    public void deleteBookmarkItem(long id) {

    }
}
