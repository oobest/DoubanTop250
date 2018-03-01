package com.albertou.study.doubantop250.model.entity;

/**
 * Created by oujianfeng on 2018/3/1.
 */

public class Cast {
    private String id;
    private String name;
    private String alt;
    private Image avatars;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Image getAvatars() {
        return avatars;
    }

    public void setAvatars(Image avatars) {
        this.avatars = avatars;
    }
}
