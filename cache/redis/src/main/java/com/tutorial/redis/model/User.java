package com.tutorial.redis.model;

import com.megvii.framework.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String id;
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
