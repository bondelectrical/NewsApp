package nlt.bondarenko.newsapp.network;

import nlt.bondarenko.newsapp.BuildConfig;
import nlt.bondarenko.newsapp.network.api.ArticleResponseApi;
import nlt.bondarenko.newsapp.network.api.SourceResponseApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static Retrofit retrofit;

    private static synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static SourceResponseApi getSourceResponseApi() {
        return getRetrofit().create(SourceResponseApi.class);
    }

    public static ArticleResponseApi getArticleResponseApi() {
        return getRetrofit().create(ArticleResponseApi.class);
    }


}
