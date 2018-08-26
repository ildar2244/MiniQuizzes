package ru.axdar.miniquizzes.model.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.axdar.miniquizzes.BuildConfig;
import ru.axdar.miniquizzes.model.Config;

/**
 * Created by ildar2244 on 20.08.2018.
 */
public class ApiClient {

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    /**
     * Создание интерцептора для логирования запросов
     * может добавлю аннотауию авторизации
     */
    private static OkHttpClient createOkHttpClient() {
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        }
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    /**
     * Создание Retrofit-объекта для дальнейших запросов
     */
    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Config.DATABASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    public static IApiClient connectDatabase() {
        return createRetrofit().create(IApiClient.class);
    }
}
