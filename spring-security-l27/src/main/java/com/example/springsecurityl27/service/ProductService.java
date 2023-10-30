package com.example.springsecurityl27.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    /*
     * @PreAuthorize   -> validate auth roles BEFORE calling method
     * @PostAuthorize  -> validate auth roles AFTER calling method. Method call avery time
     *
     * @PreFilter      -> the method need to have the parameter of type Collection or array
     *    the aspects intercepts the method cal and validated the values inside the collection
     *
     * @PostFilter     -> returned value need to be a Collection or an array
     *    the aspect applies the auth rules and returns only the values that comply
     */

    //    @PreAuthorize("hasAuthority('write')") //тоже самое что и аналогичная проверка в endpoint config'е.
    @PreAuthorize("#userName.equals(authentication.name)")
    public List<String> findProductsForUser(String userName) {
        return List.of("beer", " chocolate");
    }

}
