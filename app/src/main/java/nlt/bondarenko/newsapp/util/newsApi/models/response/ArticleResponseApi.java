package nlt.bondarenko.newsapp.util.newsApi.models.response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleResponseApi {

    @GET("https://newsapi.org/v2/top-headlines?country=us")
    Call<ArticleResponse> getSourcesArticle(@Query("apiKey") String apiKey);

    @GET("https://newsapi.org/v2/everything?")
    Call<ArticleResponse> getSourcesSearch(@Query("q") String search, @Query("apiKey") String apiKey);
}
