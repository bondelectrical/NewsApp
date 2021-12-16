package nlt.bondarenko.newsapp.util.newsApi.network;

import nlt.bondarenko.newsapp.BuildConfig;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponseApi;
import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponseApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static Retrofit retrofit;


//    public static NetworkService getNetworkService() {
//        if(networkService != null) {
//            return networkService;
//        }
//        synchronized (NetworkService.class) {
//            if(networkService ==  null) {
//                networkService = new NetworkService();
//            }
//            return networkService;
//        }
//    }

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
