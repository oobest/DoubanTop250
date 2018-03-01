package com.albertou.study.doubantop250.model.entity;

import java.util.List;

/**
 * Created by oujianfeng on 2018/3/1.
 */

public class Top250Res {
    private int start;
    private int count;
    private int total;
    private String title;
    private List<Movie> subjects;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Movie> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Movie> subjects) {
        this.subjects = subjects;
    }
}
