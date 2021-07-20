package com.tutorial.redis.controller;

import com.megvii.framework.util.JsonUtil;
import com.tutorial.redis.model.Organization;
import com.tutorial.redis.model.User;
import com.tutorial.redis.service.ITestService;
import com.tutorial.redis.service.TestService;
import com.tutorial.redis.service.WrapService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cache")
public class CacheController {
    @Autowired
    private WrapService wrapService;

    @PostMapping("user")
    public String cacheUser(User user) {
        return JsonUtil.toJson(wrapService.testuserKey(user));
    }

    @GetMapping("user")
    public String getUser() {
        return JsonUtil.toJson(wrapService.getUser());
    }

    @PostMapping("organization")
    public String cacheOrganization(Organization organization) {
        return JsonUtil.toJson(wrapService.testOrganizationKey(organization));
    }

    @PostMapping("bytes")
    public Response getByte(Response request) {
        Response response = new Response();
        response.setBytes("asdfghjkl".getBytes());
        return response;
    }
}
@Data
class Response {
    private byte[] bytes;
}
