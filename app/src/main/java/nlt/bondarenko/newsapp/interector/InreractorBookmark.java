package nlt.bondarenko.newsapp.interector;

import android.content.Context;

import java.util.List;

import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksUrl;

public interface InreractorBookmark {

    List<NewsBookMarksEntity> getNewsBookMarks(Context context);

    NewsBookMarksUrl getNewsBookMarksUrl(Context context, long id);

    void deleteNewsBookMarks(Context context, long id);

    void setNewsBookMarks(Context context, NewsBookMarksEntity newsBookMarksEntity);

}
