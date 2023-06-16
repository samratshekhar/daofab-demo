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
import com.daofab.demo.model.ParentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ParentServiceFileImpl implements ParentService {

    private String DATA_FILE_PATH = "parent.json";
    private int pageSize = 2;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ParentResponse getParentData(int page, String sortBy) {
        List<Parent> parents = null;

        try {
            parents = readDataFromFile();
        } catch (Exception e) {
            log.error("Error in reading parent.json", e);
        }
        if (parents == null) {
            return null;
        }
        // Apply sorting
        parents.sort((p1, p2) -> {
            if (sortBy.equals("id")) {
                return Integer.compare(p1.getId(), p2.getId());
            } else {
                return p1.getSender().compareToIgnoreCase(p2.getSender());
            }
        });

        // Apply pagination
        int fromIndex = page * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, parents.size());

        ParentResponse response = new ParentResponse();
        response.setData(parents.subList(fromIndex, toIndex));
        response.setTotalPage((int) Math.ceil((double) parents.size() / (double) pageSize));
        return response;
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
