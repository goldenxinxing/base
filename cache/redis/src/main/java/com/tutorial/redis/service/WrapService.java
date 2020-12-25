package com.tutorial.redis.service;

import com.tutorial.redis.model.Organization;
import com.tutorial.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WrapService {
    @Autowired
    private ITestService testService;
    public Map<String, User> testuserKey(User user) {
        return testService.testuserKey(user);
    }

    public User getUser() {
        return testService.getUser();
    }

    public Map<String, Organization> testOrganizationKey(Organization organization) {
        return testService.testOrganizationKey(organization);
    }
}
