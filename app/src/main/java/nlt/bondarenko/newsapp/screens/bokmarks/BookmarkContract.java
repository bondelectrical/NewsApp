package nlt.bondarenko.newsapp.screens.bokmarks;

import java.util.List;

import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public interface BookmarkContract {

    interface BookmarkView {

        void updateBookMarksList(List<ArticleBookMarksEntity> newsBookMarksEntities);

        void showArticle(String url);

        void shareArticleBookMarks(ArticleBookMarksEntity news);

    }

    interface BookmarkPresenter {

        void attach(BookmarkView view);

        void detach();

        void getBookmarkList();

        void deleteBookmarkItem(ArticleBookMarksEntity news);

        void shareBookmarkArticle(ArticleBookMarksEntity news);

        void showArticleWebView(String url);

    }


}
