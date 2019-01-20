package com.fancychuan.springmvc.controller;

import com.fancychuan.springmvc.entity.FileInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class FileOperatorController {

    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file,
                         @ModelAttribute FileInfo fileinfo, Model model) throws Exception {
        if (file.isEmpty()) {
            return "upload-failure";
        } else {
            // 上传路径
            String path = request.getServletContext().getRealPath("/images");
            // 上传文件的原始文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            if(!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            System.out.println("文件路径: " + path + File.separator + filename); // 文件路径: D:\ProgramData\apache-tomcat-8.5.37\webapps\ROOT\images\Koala.jpg
            // File.separator 文件路径的分隔符
            file.transferTo(new File(path + File.separator + filename));
            // return "upload-success";
            // 把下载信息转到model中
            model.addAttribute("fileinfo", fileinfo);
            return "download"; // 跳转到下载页面
        }
    }

    // 下载需要返回一个二进制流，这里用ResponseEntity
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename) throws Exception {
        String path = request.getServletContext().getRealPath("/images");
        File file = new File(path, filename);
        HttpHeaders headers = new HttpHeaders();
        // 解决中文乱码
        String downloadFile = new String(filename.getBytes("UTF-8"), "gbk");
        // 以下载的方式打开文件
        headers.setContentDispositionFormData("attachment", downloadFile);
        // 二进制流
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
