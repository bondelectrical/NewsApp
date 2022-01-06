package nlt.bondarenko.newsapp.screens.bokmarks;

import java.util.List;

import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;

public interface BookmarkContract {

    interface BookmarkView {

        void updateBookMarksList(List<NewsBookMarksEntity> newsBookMarksEntities);

    }

    interface BookmarkPresenter {

        void attach(BookmarkView view);

        void detach();

        void getBookmarkList();

        void deleteBookmarkItem(NewsBookMarksEntity news);

    }


}
