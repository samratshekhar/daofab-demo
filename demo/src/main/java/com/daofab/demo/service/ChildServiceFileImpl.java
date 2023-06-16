package com.daofab.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.daofab.demo.model.Child;
import com.daofab.demo.model.ChildData;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChildServiceFileImpl implements ChildService {

    private String DATA_FILE_PATH = "child.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Child> getChildData(int parentId) throws IOException {
        List<Child> children = readDataFromFile();
        return children.stream().filter(child -> child.getParentId() == parentId).collect(Collectors.toList());
    }

    private List<Child> readDataFromFile() throws IOException {
        File parentFile = loadParentsWithSpringInternalClass();
        String content = new String(Files.readAllBytes(parentFile.toPath()));
        return parseData(content).getData();
    }

    public static ChildData parseData(String json) throws IOException {
        return objectMapper.readValue(json, ChildData.class);
    }

    public File loadParentsWithSpringInternalClass() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:" + DATA_FILE_PATH);
    }

}
