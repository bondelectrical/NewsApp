package nlt.bondarenko.newsapp.interactor;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;

import nlt.bondarenko.newsapp.repository.Repository;
import nlt.bondarenko.newsapp.repository.RepositoryImpl;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;

public class ArticleInteractorImpl implements ArticleInteractor {

    private final Repository repository = RepositoryImpl.getRepositoryImpl();

    @Override
    public ArticleResponse getArticleListNews() throws IOException {
        return repository.getArticleSourceList();
    }

    @Override
    public void setNewsBookMarks(Context context, Article article) {
        NewsBookMarksEntity newsBookMarksEntity = new NewsBookMarksEntity(article.getName(),
                article.getTitle(), article.getDescription(),
                article.getUrl(), article.getUrlToImage());
        repository.setNewsBookMarksEntity(context, newsBookMarksEntity);
    }

    @Override
    public void shareArticleNews(Context context, Article article) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
        sendIntent.putExtra(Intent.EXTRA_TITLE, article.getTitle());
        sendIntent.setType("text/plain");
        if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(sendIntent, null));
        } else {
            Toast.makeText(context, "No app to send email. Please install at least one",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
