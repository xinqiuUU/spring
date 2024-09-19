package com.yc.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class UserController {
    private final String uploadPath = System.getProperty("user.home") + File.separator + "uploads";

    @PostMapping(value = "/doUpload" ,
            produces = "text/html;charset=UTF-8")//响应的内容类型)
    public String registerUser(
            @RequestParam("username") String name,
            @RequestParam("pwd") String pwd,
            @RequestParam("avatar") MultipartFile avatar) {

        if (avatar.isEmpty()) {
            return "头像上传失败，文件为空！";
        }

        String fileName = UUID.randomUUID().toString() + "_" + avatar.getOriginalFilename();
        File dest = new File(uploadPath + File.separator + fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            avatar.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return "头像上传失败！";
        }

        return "用户注册成功！" +"用户名:"+name+"密码:"+pwd;
    }
}