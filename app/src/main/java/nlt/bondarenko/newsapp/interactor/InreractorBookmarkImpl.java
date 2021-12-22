package nlt.bondarenko.newsapp.interactor;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksUrl;

public class InreractorBookmarkImpl implements InreractorBookmark {

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

    @Override
    public void shareNewsBookMarks(Context context, NewsBookMarksEntity newsBookMarksEntity) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, newsBookMarksEntity.getUrl());
        sendIntent.putExtra(Intent.EXTRA_TITLE, newsBookMarksEntity.getTitle());
        sendIntent.setType("text/plain");
        if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(sendIntent, null));
        } else {
            Toast.makeText(context, "No app to send email. Please install at least one",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
