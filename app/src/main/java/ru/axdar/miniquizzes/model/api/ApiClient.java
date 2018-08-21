package ru.axdar.miniquizzes.model.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.axdar.miniquizzes.BuildConfig;

/**
 * Created by ildar2244 on 20.08.2018.
 */
public class ApiClient {

    //TODO: 1) установить URL-адрес; 2) метод для подключения в БД

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    /**
     * Создание интерцептора для логирования запросов
     * может добавлю аннотауию авторизации
     */
    private static OkHttpClient createOkHttpClient() {
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
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
                .baseUrl("url")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }
}
