package nlt.bondarenko.newsapp.interactor;

import android.content.Context;

import java.util.List;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksUrl;

public class BookmarkInreractorImpl implements BookmarkInreractor {

    private final Repository repository = RepositoryImpl.getRepositoryImpl();


    @Override
    public List<NewsBookMarksEntity> getNewsBookMarks(Context context) {
        return repository.getNewsBookMarksEntity(context);
    }

    @Override
    public NewsBookMarksUrl getNewsBookMarksUrl(Context context, long id) {
        return repository.getNewsBookMarksUrl(context, id);
    }

    @Override
    public void deleteNewsBookMarks(Context context, NewsBookMarksEntity news) {
        repository.deleteNewsBookMarksEntity(context, news);
    }

    @Override
    public void setNewsBookMarks(Context context, NewsBookMarksEntity newsBookMarksEntity) {
        repository.setNewsBookMarksEntity(context, newsBookMarksEntity);
    }

}
