package com.daofab.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.demo.model.Child;
import com.daofab.demo.service.ChildService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ChildController {

    @Autowired
    private ChildService childService;

    @GetMapping("/children")
    public ResponseEntity<List<Child>> getChildren(
            @RequestParam(defaultValue = "1") int parentId) {
        List<Child> children = null;
        try {
            children = childService.getChildData(parentId);
        } catch (Exception e) {
            log.error(e);
        }
        if (children != null) {
            return ResponseEntity.ok().body(children);
        }
        return ResponseEntity.internalServerError().build();
    }
}
