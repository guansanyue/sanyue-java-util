package com.sanyue.sanyuejavautil.controller;

import com.alibaba.excel.EasyExcel;
import com.sanyue.sanyuejavautil.model.entity.read.DemoData;
import com.sanyue.sanyuejavautil.util.excel.read.DemoDataListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), DemoData.class, new DemoDataListener()).sheet().doRead();
        return "success";
    }

    @PostMapping("upload1")
    @ResponseBody
    public String upload1(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), DemoData.class, new DemoDataListener())
                .doReadAll();   // 读取所有 sheet
        return "success";
    }

}
