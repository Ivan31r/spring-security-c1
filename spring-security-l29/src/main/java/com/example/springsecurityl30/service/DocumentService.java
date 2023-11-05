package com.example.springsecurityl30.service;

import com.example.springsecurityl30.model.Document;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @PostAuthorize("hasPermission(returnObject,'read')")
    public List<Document> findDocument(String userName) {
        var doc = new Document();
        doc.setUser("john");
        doc.setText("some text");
        return List.of(doc);
    }
}
