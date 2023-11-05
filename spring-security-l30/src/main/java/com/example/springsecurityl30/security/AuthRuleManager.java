package com.example.springsecurityl30.security;

import com.example.springsecurityl30.model.Document;

import java.util.List;

public interface AuthRuleManager {
    boolean appleSecurityPermissions(List<Document> returnObject, String auth);
}
//Alt + f7 - найти реализации