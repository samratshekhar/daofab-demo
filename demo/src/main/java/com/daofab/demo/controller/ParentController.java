package com.daofab.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.demo.model.Parent;
import com.daofab.demo.model.ParentResponse;
import com.daofab.demo.service.ParentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ParentController {

    @Autowired
    private ParentService parentService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("api/parents")
    public ResponseEntity<ParentResponse> getParents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy) {
        ParentResponse response = parentService.getParentData(page, sortBy);
        log.debug("Parents: ", response);
        if (response != null) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.internalServerError().build();
    }
}
