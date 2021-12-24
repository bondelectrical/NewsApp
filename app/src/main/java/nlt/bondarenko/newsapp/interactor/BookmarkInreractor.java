package nlt.bondarenko.newsapp.interactor;

import android.content.Context;

import java.util.List;

import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksUrl;

public interface BookmarkInreractor {

    List<NewsBookMarksEntity> getNewsBookMarks(Context context);

    NewsBookMarksUrl getNewsBookMarksUrl(Context context, long id);

    void deleteNewsBookMarks(Context context, NewsBookMarksEntity news);

    void setNewsBookMarks(Context context, NewsBookMarksEntity newsBookMarksEntity);

}
