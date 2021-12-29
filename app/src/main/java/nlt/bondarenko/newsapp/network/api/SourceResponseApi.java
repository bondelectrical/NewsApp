package nlt.bondarenko.newsapp.network.api;

import nlt.bondarenko.newsapp.network.models.SourceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SourceResponseApi {

    @GET("top-headlines/sources?")
    Call<SourceResponse> getSources(@Query("apiKey") String apiKey);

}
