package com.daofab.demo.service;

import java.util.List;

import com.daofab.demo.model.Parent;

public interface ParentService {
    List<Parent> getParentData(int page, String sortBy);
}
