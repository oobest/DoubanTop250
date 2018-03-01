package com.albertou.study.doubantop250.model.api;

import com.albertou.study.doubantop250.BuildConfig;
import com.albertou.study.doubantop250.model.util.EntityUtils;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oujianfeng on 2018/3/1.
 */

public class ApiClient {
    private ApiClient(){}

    public static final ApiService service = new Retrofit.Builder()
            .baseUrl("http://api.douban.com/v2/movie/")
            .client(new OkHttpClient.Builder()
                    .addInterceptor(createHttpLoggingInterceptor())
                    .build())
            .addConverterFactory(GsonConverterFactory.create(EntityUtils.gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService.class);


    private static Interceptor createHttpLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG? HttpLoggingInterceptor.Level.BODY: HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }
}
