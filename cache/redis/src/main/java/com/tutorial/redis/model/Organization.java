package com.tutorial.redis.model;

import com.megvii.framework.util.JsonUtil;
import lombok.Data;

@Data
public class Organization {
    private String name;
    private String id;
    private String parentId;
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
