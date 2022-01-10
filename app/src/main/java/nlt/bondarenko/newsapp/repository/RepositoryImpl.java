package nlt.bondarenko.newsapp.repository;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import nlt.bondarenko.newsapp.BuildConfig;
import nlt.bondarenko.newsapp.NewsApp;
import nlt.bondarenko.newsapp.network.NetworkService;
import nlt.bondarenko.newsapp.network.models.ArticleResponse;
import nlt.bondarenko.newsapp.network.models.SourceResponse;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public class RepositoryImpl implements Repository {

//    private static RepositoryImpl repository;


//    public static RepositoryImpl getRepositoryImpl() {
//        if (repository == null) {
//            synchronized (RepositoryImpl.class) {
//                repository = new RepositoryImpl();
//            }
//        }
//        return repository;
//    }

    @Override
    public SourceResponse getSourceList() {
        SourceResponse sourceResponse = null;
        try {
            sourceResponse = NetworkService.getSourceResponseApi().getSources(BuildConfig.API_KEY).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceResponse;
    }

    @Override
    public ArticleResponse getArticleList() {
        String country = Locale.getDefault().getCountry();
        ArticleResponse articleResponse = null;
        try {
            articleResponse = NetworkService.getArticleResponseApi().getSourcesArticle(country, BuildConfig.API_KEY).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articleResponse;
    }

    @Override
    public ArticleResponse getSearchArticleResponseList(String searchNews) {
        ArticleResponse articleResponse = null;
        try {
            articleResponse = NetworkService.getArticleResponseApi().getSearchArticle(searchNews, BuildConfig.API_KEY).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articleResponse;
    }

    @Override
    public List<ArticleBookMarksEntity> getArticleBookMarks() {
        return NewsApp.getAppDataBase().articleBookMarksDao().getAll();
    }

    @Override
    public void deleteArticleBookMarks(ArticleBookMarksEntity articleBookMarksEntity) {
        NewsApp.getAppDataBase().articleBookMarksDao().delete(articleBookMarksEntity);
    }

    @Override
    public void saveArticleBookMarks(ArticleBookMarksEntity articleBookMarksEntity) {
        NewsApp.getAppDataBase().articleBookMarksDao().insert(articleBookMarksEntity);
    }


}
