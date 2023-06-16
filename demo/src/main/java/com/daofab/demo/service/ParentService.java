package com.daofab.demo.service;

import java.io.IOException;
import java.util.List;

import com.daofab.demo.model.Parent;

public interface ParentService {
    List<Parent> getParentData(int page, String sortBy) throws IOException;
}
