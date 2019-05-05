package com.bnlucas.contactrecords.controller;

import com.bnlucas.contactrecords.domain.Contact;
import com.bnlucas.contactrecords.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping("/file")
    public ResponseEntity<Map<String, List<Contact>>> processContactFile(
            @RequestParam("file") MultipartFile multipartFile) {

        Map<String, List<Contact>> contacts = apiService.processContactFile(multipartFile);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}
