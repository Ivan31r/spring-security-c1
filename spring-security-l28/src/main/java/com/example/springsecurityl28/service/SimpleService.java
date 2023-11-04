package com.example.springsecurityl28.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleService {

    /*
    Pre - проверка до вызова целевого метода
    Post - проверка после выхода из целевого метода
    */

    @PreAuthorize("hasAnyAuthority('read')")
    public String getTest1() {
        return "TEST!1";
    }

    @PostAuthorize("hasAnyAuthority('read')")
    public String getTest2() {
        return "TEST!2";
    }

    @PostAuthorize("returnObject==authentication.name")
    public String getTest3() {
        System.out.println("TEST3");
        return "john";
    }

    /*
     * PreFilter - применяется к коллекции-параметру метода
     * PostFilter - применяется в возвращаемому значению(коллекции)
     */
    @PreFilter("filterObject=='john'")
    public List<String> test4(List<String> list) { //работает только на изменяемых коллекциях. Если передать List.of() или аналог - упадем.
        return list;
    }


    @PostFilter("filterObject!=authentication.name")
    public List<String> test5() { //работает только на изменяемых коллекциях. Если передать List.of() или аналог - упадем.
        List<String> list = new ArrayList<>();
        list.add("john");
        list.add("bill");
        list.add("mary");
        return list;
    }
}
