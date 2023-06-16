package com.daofab.demo.service;

import com.daofab.demo.model.ParentResponse;

public interface ParentService {
    ParentResponse getParentData(int page, String sortBy);
}
