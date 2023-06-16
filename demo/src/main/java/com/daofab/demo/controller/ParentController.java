package com.daofab.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParentController {

    @GetMapping("/")
    public ResponseEntity<String> getParents() {
        return ResponseEntity.ok().body("null");
    }
}
