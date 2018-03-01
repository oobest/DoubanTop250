package com.albertou.study.doubantop250.model.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by oujianfeng on 2018/3/1.
 */

public class EntityUtils {
    private EntityUtils(){

    }

    public static final Gson gson = new GsonBuilder()
            .create();
}
