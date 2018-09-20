package com.example.demo.controller;

import com.example.demo.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.MessageFormat;

@Controller
@Slf4j
public class UploadFileController {

    @PostMapping("/fileUpload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        log.info(MessageFormat.format("上传文件 {0} ", file.getOriginalFilename()));
        try {
            // TODO location to constant
            FileUtil.saveFile(file, "./temp");
        } catch (Exception e) {
            log.error("ile save fail: ", e);
           return "file save fail, please contract the manager";
        }
        return file.getOriginalFilename();
    }
}
