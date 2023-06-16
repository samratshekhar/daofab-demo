package com.daofab.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.daofab.demo.model.Parent;
import com.daofab.demo.model.ParentData;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ParentServiceFileImpl implements ParentService {

    private String DATA_FILE_PATH = "parent.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Parent> getParentData() throws IOException {
        List<Parent> parents = readDataFromFile();
        return parents;
    }

    private List<Parent> readDataFromFile() throws IOException {
        File parentFile = loadParentsWithSpringInternalClass();
        String content = new String(Files.readAllBytes(parentFile.toPath()));
        return parseData(content).getData();
    }

    public static ParentData parseData(String json) throws IOException {
        return objectMapper.readValue(json, ParentData.class);
    }

    public File loadParentsWithSpringInternalClass() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:" + DATA_FILE_PATH);
    }

}
