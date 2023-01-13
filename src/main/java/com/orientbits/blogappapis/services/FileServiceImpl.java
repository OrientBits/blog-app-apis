package com.orientbits.blogappapis.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename(); //abc.png
        String fileName = UUID.randomUUID().toString().concat(name.substring(name.lastIndexOf(".")));
        String filePath = path + File.separator + fileName;

        File f = new File(path);
        if (!f.exists())
            f.mkdir();

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        // do logic to return inputStream

        return is;
    }

}
