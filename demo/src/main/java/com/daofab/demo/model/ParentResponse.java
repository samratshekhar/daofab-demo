package com.daofab.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class ParentResponse {

    List<Parent> data;
    int totalPage;
}
