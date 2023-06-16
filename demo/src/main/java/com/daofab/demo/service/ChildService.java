package com.daofab.demo.service;

import java.util.List;

import com.daofab.demo.model.Child;

public interface ChildService {
    List<Child> getChildData(int parentId);

}
