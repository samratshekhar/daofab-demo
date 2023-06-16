package com.daofab.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.demo.model.Parent;
import com.daofab.demo.service.ParentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ParentController {

    @Autowired
    private ParentService parentService;

    @GetMapping("/parents")
    public ResponseEntity<List<Parent>> getParents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<Parent> parents = null;
        parents = parentService.getParentData(page, sortBy);
        log.debug("Parents: ", parents);
        if (parents != null) {
            return ResponseEntity.ok().body(parents);
        }
        return ResponseEntity.internalServerError().build();
    }
}
