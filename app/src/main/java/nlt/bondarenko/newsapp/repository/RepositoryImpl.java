package nlt.bondarenko.newsapp.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import nlt.bondarenko.newsapp.BuildConfig;
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

}
