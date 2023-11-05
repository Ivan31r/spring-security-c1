package com.example.springsecurityl30.controller;

import com.example.springsecurityl30.model.Document;
import com.example.springsecurityl30.service.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents/{username}")
    public List<Document> findDocument(@PathVariable String username) {
        return documentService.findDocument(username);
    }
}
