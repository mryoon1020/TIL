package com.example.springawss3.controller;

import com.example.springawss3.model.FileDetail;
import com.example.springawss3.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
public class UploadController {
    @Autowired
    @Qualifier("com.example.springawss3.service.serviceImpl");
        private final FileUploadService fileUploadService;

        @GetMapping("./")
        public String Home{

            return "./";
    }
    @ResponseBody
    @RequestMapping(value = "/upload", produces = APPLICATION_JSON_VALUE)
    @PostMapping
    public ResponseEntity<FileDetail> post(
            @RequestPart("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(fileUploadService.save(multipartFile));
    }
}