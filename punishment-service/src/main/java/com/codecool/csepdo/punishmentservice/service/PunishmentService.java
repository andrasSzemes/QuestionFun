package com.codecool.csepdo.punishmentservice.service;

import com.codecool.csepdo.punishmentservice.model.Punishment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
public class PunishmentService {

    @Value("${filepath}")
    private String filepath;

    private List<String> urls = new ArrayList<>();

    private Random random = new Random();

    public Punishment newVideo() {
        readFile();
        String path = urls.get(random.nextInt(urls.size()));
        File video = new File(path);
        byte[] videoBytes = new byte[(int) video.length()];
        try {
            FileInputStream videoByteStream = new FileInputStream(video);
            videoByteStream.read(videoBytes);
            videoByteStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String base64Video = Base64.getEncoder().withoutPadding().encodeToString(videoBytes);

        return new Punishment("data:video/mp4;base64," + base64Video);
    }

    public void readFile() {
        try {
            urls = Files.readAllLines(Paths.get("punishment-service/src/main/resources/links.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
