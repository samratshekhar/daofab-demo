package com.daofab.demo.service;

import java.io.IOException;
import java.util.List;

import com.daofab.demo.model.Child;

public interface ChildService {
    List<Child> getChildData(int parentId) throws IOException;

}
