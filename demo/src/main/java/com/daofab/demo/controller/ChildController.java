package com.daofab.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.demo.model.Child;
import com.daofab.demo.service.ChildService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ChildController {

    @Autowired
    private ChildService childService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("api/children")
    public ResponseEntity<List<Child>> getChildren(
            @RequestParam(defaultValue = "1") int parentId) {
        List<Child> children = null;
        children = childService.getChildData(parentId);
        log.debug("Children: ", children);
        if (children != null) {
            return ResponseEntity.ok().body(children);
        }
        return ResponseEntity.internalServerError().build();
    }
}
