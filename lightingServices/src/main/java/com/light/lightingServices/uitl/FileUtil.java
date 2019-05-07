package com.light.lightingServices.uitl;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FileUtil {

    public static String save(MultipartFile file, String targetPath) throws IOException {
        byte[] imgByte = file.getBytes();
        DateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = dataFormat.format(System.currentTimeMillis())+file.getOriginalFilename();
        File targetFile = new File(targetPath);
        if(!targetFile.exists())
        {
            targetFile.mkdirs();
        }

        Path path = Paths.get(targetPath,fileName);
        Files.write(path,imgByte);

        return fileName;
    }


}
