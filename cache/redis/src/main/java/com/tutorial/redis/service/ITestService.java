package com.tutorial.redis.service;

import com.tutorial.redis.model.Organization;
import com.tutorial.redis.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

public abstract class ITestService {

    @Cacheable("globalCache")
    public abstract Map<String, User> testuserKey(User user);

    @Cacheable("globalCache")
    public abstract User getUser();

    @Cacheable("globalCache")
    public abstract Map<String, Organization> testOrganizationKey(Organization organization);
}
