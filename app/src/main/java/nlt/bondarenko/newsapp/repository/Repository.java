package nlt.bondarenko.newsapp.repository;

import java.util.List;

import nlt.bondarenko.newsapp.network.models.ArticleResponse;
import nlt.bondarenko.newsapp.network.models.SourceResponse;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public interface Repository {

    //Network method

    SourceResponse getSourceList();

    ArticleResponse getArticleList();

    ArticleResponse getSearchArticleResponseList(String searchNews);

    //Data base method

    List<ArticleBookMarksEntity> getArticleBookMarks();

    void deleteArticleBookMarks(ArticleBookMarksEntity articleBookMarksEntity);

    void saveArticleBookMarks(ArticleBookMarksEntity articleBookMarksEntity);

}
