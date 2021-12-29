package nlt.bondarenko.newsapp.interactor;

import java.util.List;

import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public interface BookmarkInreractor {

    List<ArticleBookMarksEntity> getArticleBookMarks();

    void deleteArticleBookMarks(ArticleBookMarksEntity article);


}
