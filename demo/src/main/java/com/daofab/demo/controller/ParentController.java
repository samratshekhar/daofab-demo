package com.daofab.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.demo.model.Parent;
import com.daofab.demo.service.ParentService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ParentController {

    @Autowired
    private ParentService parentService;

    @GetMapping("/")
    public ResponseEntity<String> getParents() {
        try {
            List<Parent> parents = parentService.getParentData();
            log.debug("Parents: ", parents);
        } catch (Exception e) {
            log.error(e);
        }
        return ResponseEntity.ok().body("null");
    }
}
