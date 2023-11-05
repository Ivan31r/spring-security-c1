package com.example.springsecurityl30.security;

import com.example.springsecurityl30.model.Document;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.List;

public class DocumentPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject,
                                 Object permission) {
        List<Document> returnedList = (List<Document>) targetDomainObject;
        String name = authentication.getName();
        String auth = (String) permission;

        boolean match = returnedList.stream().allMatch(document -> document.getUser().equals(name));

        boolean hasProperAuth = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(auth));

        return match && hasProperAuth;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }
}
