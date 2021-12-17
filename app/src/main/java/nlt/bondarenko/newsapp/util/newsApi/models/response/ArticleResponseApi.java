package nlt.bondarenko.newsapp.util.newsApi.models.response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ArticleResponseApi {

    @GET("https://newsapi.org/v2/top-headlines?country=ua")
    Call<ArticleResponse> getSourcesArticle(@Query("apiKey") String apiKey);

    @GET("https://newsapi.org/v2/top-headlines")
    Call<ArticleResponse> getSourcesArticle(@QueryMap Map<String, String> query);

}
