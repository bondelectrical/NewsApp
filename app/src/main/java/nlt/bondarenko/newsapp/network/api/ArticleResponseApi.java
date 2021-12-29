package nlt.bondarenko.newsapp.network.api;

import nlt.bondarenko.newsapp.network.models.ArticleResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleResponseApi {

    @GET("https://newsapi.org/v2/top-headlines")
    Call<ArticleResponse> getSourcesArticle(@Query("country") String country, @Query("apiKey") String apiKey);


    @GET("https://newsapi.org/v2/top-headlines")
    Call<ArticleResponse> getSearchArticle(@Query("q") String country, @Query("apiKey") String apiKey);

}
