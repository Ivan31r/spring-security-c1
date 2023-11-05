package com.example.springsecurityl30.service;

import com.example.springsecurityl30.model.Document;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class DocumentService {

    //    @PostAuthorize("hasPermission(returnObject,'read')")
//    @PostAuthorize("@documentMethodAuthManager.appleSecurityPermissions(returnObject,'read')")
//    @Secured("ROLE_MANAGER") //userJohn
    @RolesAllowed("ROLE_MANAGER")
    public List<Document> findDocument(String userName) {
        var doc = new Document();
        doc.setUser("john");
        doc.setText("some text");
        return List.of(doc);
    }
}
