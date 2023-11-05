package com.example.springsecurityl30.security;

import com.example.springsecurityl30.model.Document;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentMethodAuthManager implements AuthRuleManager {
    public boolean appleSecurityPermissions(List<Document> returnObject, String auth) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();       //Username

        boolean match = returnObject.stream().allMatch(document -> document.getUser().equals(username));

        boolean hasProperAuth = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(auth)); //все authorities

        return match && hasProperAuth;
    }
}
