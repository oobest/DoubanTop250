package com.albertou.study.doubantop250.model.entity;

/**
 * Created by oujianfeng on 2018/3/1.
 */

public class Rating {
    private int max;
    private int min;
    private String stars;
    private float average;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
