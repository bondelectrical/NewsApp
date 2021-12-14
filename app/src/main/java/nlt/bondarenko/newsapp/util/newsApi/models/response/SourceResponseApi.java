package nlt.bondarenko.newsapp.util.newsApi.models.response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SourceResponseApi {

    @GET("top-headlines/sources?apiKey=f41bce9f28454537a9a89222110e029f")
    Call<SourceResponse> getSources();
}
