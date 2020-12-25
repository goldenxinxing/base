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

    @Cacheable("globalCache")
    public Map<String, User> testuserKey(User user) {
        return Collections.singletonMap(user.getId(), user);
    }

    @Override
    @Cacheable("globalCache-get")
    public User getUser() {
        return new User("user", "10001");
    }

    @Cacheable(value = "globalCache")
    public Map<String, Organization> testOrganizationKey(Organization organization) {
        return Collections.singletonMap(organization.getId(), organization);
    }
}
