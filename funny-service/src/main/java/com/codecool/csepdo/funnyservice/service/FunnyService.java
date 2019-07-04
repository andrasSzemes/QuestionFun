package com.codecool.csepdo.funnyservice.service;

import com.codecool.csepdo.funnyservice.model.FunnyImg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FunnyService {

    @Value("${filepath}")
    private String filepath;

    private List<String> urls = new ArrayList<>();

    private Random random = new Random();

    public FunnyImg newImage() {
        readFile();
        return new FunnyImg(urls.get(random.nextInt(urls.size())));
    }

    public void readFile() {
        try {
            urls = Files.readAllLines(Paths.get("funny-service/src/main/resources/links.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
