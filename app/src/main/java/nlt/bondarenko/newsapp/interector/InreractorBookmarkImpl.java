package nlt.bondarenko.newsapp.interector;

import android.content.Context;

import java.util.List;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksUrl;

public class InreractorBookmarkImpl implements InreractorBookmark {

    private Repository repository = RepositoryImpl.getRepositoryImpl();


    @Override
    public List<NewsBookMarksEntity> getNewsBookMarks(Context context) {
        return repository.getNewsBookMarksEntity(context);
    }

    @Override
    public NewsBookMarksUrl getNewsBookMarksUrl(Context context, long id) {
        return repository.getNewsBookMarksUrl(context, id);
    }

    @Override
    public void deleteNewsBookMarks(Context context, long id) {
        repository.deleteNewsBookMarksEntity(context, repository.getNewsBookMarksEntity(context, id));
    }

    @Override
    public void setNewsBookMarks(Context context, NewsBookMarksEntity newsBookMarksEntity) {
        repository.setNewsBookMarksEntity(context, newsBookMarksEntity);
    }
}
