package nlt.bondarenko.newsapp.util.newsApi.network;

import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponseApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static String BASE_URL = "https://newsapi.org/v2/";
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
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static SourceResponseApi getSourceResponseApi() {
        return getRetrofit().create(SourceResponseApi.class);
    }
}
