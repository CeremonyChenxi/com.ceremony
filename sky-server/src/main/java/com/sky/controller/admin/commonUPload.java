package com.sky.controller.admin;

import com.sky.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

@RestController
public class commonUPload {

//    http://localhost/api/common/upload
//    post
    @PostMapping("/admin/common/upload")
    public Result FileUPload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        //文件原名
        String originalFilename = multipartFile.getOriginalFilename();
        URL url = ResourceUtils.getURL("classpath:");
        String path = url.getPath();
        //路径名
        int year = LocalDateTime.now().getYear();
        int monthValue = LocalDateTime.now().getMonthValue();
        int dayOfMonth = LocalDateTime.now().getDayOfMonth();
        UUID uuid = UUID.randomUUID();
        String filePath=path+"/"+year+"/"+monthValue+"/"+dayOfMonth;
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        String dir = file.getPath();
        multipartFile.transferTo(Paths.get(dir +"/"+ uuid + "_" + originalFilename));
        return Result.success(dir +"/"+ uuid + "_" + originalFilename);


    }
}
