package com.daofab.demo.model;

import lombok.Data;

@Data
public class Parent {

    private int id;
    private String sender;
    private String receiver;
    private int totalAmount;

}
