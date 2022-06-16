package Services;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateConnection {
    Retrofit retrofit;
    public CreateConnection(String token){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        String base_Url = "http://10.0.2.2:3000/";
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public CreateConnection(){
        String base_Url = "http://10.0.2.2:3000/";
        retrofit = new Retrofit.Builder()
                .baseUrl(base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PlaceHolder CreatePlaceHolder(){
        return retrofit.create(PlaceHolder.class);
    }
}
