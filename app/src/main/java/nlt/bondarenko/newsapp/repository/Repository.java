package nlt.bondarenko.newsapp.repository;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksUrl;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;
import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;

public interface Repository {

    //Network method

    SourceResponse getSourceList() throws IOException;

    ArticleResponse getArticleSourceList() throws IOException;

    ArticleResponse getArticleResponseSearchList(String searchNews) throws IOException;

    //Data base method

    List<NewsBookMarksEntity> getNewsBookMarksEntity(Context context);

    NewsBookMarksEntity getNewsBookMarksEntity(Context context, long id);

    NewsBookMarksUrl getNewsBookMarksUrl(Context context, long id);

    void deleteNewsBookMarksEntity(Context context, NewsBookMarksEntity newsBookMarksEntity);

    void setNewsBookMarksEntity(Context context, NewsBookMarksEntity newsBookMarksEntity);


}
