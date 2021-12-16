package nlt.bondarenko.newsapp.repository;

import java.io.IOException;

import nlt.bondarenko.newsapp.BuildConfig;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;
import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;
import nlt.bondarenko.newsapp.util.newsApi.network.NetworkService;

public class RepositoryImpl implements Repository {

    @Override
    public SourceResponse getSourceList() throws IOException {
        return NetworkService.getSourceResponseApi().getSources(BuildConfig.API_KEY).execute().body();
    }

    @Override
    public ArticleResponse getArticleSourceList() throws IOException {
        return NetworkService.getArticleResponseApi().getSourcesArticle(BuildConfig.API_KEY).execute().body();
    }

    @Override
    public ArticleResponse getArticleResponseSearchList(String searchNews) throws IOException {
        return NetworkService.getArticleResponseApi().getSourcesSearch(searchNews, BuildConfig.API_KEY).execute().body();
    }

}
