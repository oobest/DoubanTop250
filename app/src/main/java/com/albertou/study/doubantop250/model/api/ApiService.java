package com.albertou.study.doubantop250.model.api;

import com.albertou.study.doubantop250.model.entity.Top250Res;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by oujianfeng on 2018/2/28.
 */

public interface ApiService {

    @GET("top250")
    Observable<Top250Res> getTop250(@Query("start") Integer start, @Query("count") Integer count);
}
