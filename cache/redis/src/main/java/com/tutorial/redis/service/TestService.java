package com.tutorial.redis.service;

import com.tutorial.redis.model.Organization;
import com.tutorial.redis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@CacheConfig(cacheNames = "test_cache")
public class TestService extends ITestService {

    @Cacheable(value = "globalCache",key = "#user.id")
    public Map<String, User> testuserKey(User user) {
        return Collections.singletonMap(user.getId(), user);
    }

    @Override
    @Cacheable(value = "globalCache-get",key = "'getUser'")
    public User getUser() {
        return new User("user", "10001");
    }

    @Cacheable(value = "globalCache", key = "#organization.id")
    public Map<String, Organization> testOrganizationKey(Organization organization) {
        return Collections.singletonMap(organization.getId(), organization);
    }
}
