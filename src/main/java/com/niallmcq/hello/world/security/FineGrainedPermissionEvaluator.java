package com.niallmcq.hello.world.security;

import com.niallmcq.hello.world.entity.User;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class FineGrainedPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }

    public boolean hasPermission(final Long userId, final Authentication authentication) {
        final User requestingUser = (User) authentication.getPrincipal();
        return requestingUser.getId().equals(userId);
    }
}
