package nlt.bondarenko.newsapp.util.newsApi.models.response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SourceResponseApi {

    @GET("top-headlines/sources?")
    Call<SourceResponse> getSources(@Query("apiKey") String apiKey);
}
