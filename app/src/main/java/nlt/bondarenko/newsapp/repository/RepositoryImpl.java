package nlt.bondarenko.newsapp.repository;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import nlt.bondarenko.newsapp.BuildConfig;
import nlt.bondarenko.newsapp.roomdatabase.database.AppDataBase;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksUrl;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;
import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;
import nlt.bondarenko.newsapp.util.newsApi.network.NetworkService;

public class RepositoryImpl implements Repository {

    private static RepositoryImpl repository;

    private RepositoryImpl() {
    }

    public static RepositoryImpl getRepositoryImpl() {
        if (repository == null) {
            synchronized (RepositoryImpl.class) {
                repository = new RepositoryImpl();
            }
        }
        return repository;
    }

    @Override
    public SourceResponse getSourceList() throws IOException {
        return NetworkService.getSourceResponseApi().getSources(BuildConfig.API_KEY).execute().body();
    }

    @Override
    public ArticleResponse getArticleSourceList() throws IOException {
        String country = Locale.getDefault().getCountry();
        Map<String, String> query = new HashMap<>();
        query.put("country", country);
        query.put("apiKey", BuildConfig.API_KEY);
        return NetworkService.getArticleResponseApi().getSourcesArticle(query).execute().body();
    }

    @Override
    public ArticleResponse getArticleResponseSearchList(String searchNews) throws IOException {
        Map<String, String> query = new HashMap<>();
        query.put("q", searchNews);
        query.put("apiKey", BuildConfig.API_KEY);
        return NetworkService.getArticleResponseApi().getSourcesArticle(query).execute().body();
    }

    @Override
    public List<NewsBookMarksEntity> getNewsBookMarksEntity(Context context) {
        return AppDataBase.getAppDataBase(context).newsBookMarksDao().getAll();
    }

    @Override
    public NewsBookMarksEntity getNewsBookMarksEntity(Context context, long id) {
        return AppDataBase.getAppDataBase(context).newsBookMarksDao().getById(id);
    }

    @Override
    public NewsBookMarksUrl getNewsBookMarksUrl(Context context, long id) {
        return AppDataBase.getAppDataBase(context).newsBookMarksDao().getUrl(id);
    }

    @Override
    public void deleteNewsBookMarksEntity(Context context, NewsBookMarksEntity newsBookMarksEntity) {
        AppDataBase.getAppDataBase(context).newsBookMarksDao().delete(newsBookMarksEntity);
    }

    @Override
    public void setNewsBookMarksEntity(Context context, NewsBookMarksEntity newsBookMarksEntity) {
        AppDataBase.getAppDataBase(context).newsBookMarksDao().insert(newsBookMarksEntity);
    }


}
