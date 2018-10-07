package com.cloudy.controller;

import com.cloudy.entity.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by ljy_cloudy on 2018/10/6.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    public static String folder = "D:\\work\\ideaPro2\\cloudy-security\\cloudy-security-demo\\src\\main\\java\\com\\cloudy\\controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws Exception {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void dowload(@PathVariable String id,
                        HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        try (
                InputStream in = new FileInputStream(new File(folder, id + ".txt"));
                OutputStream out = response.getOutputStream()
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename-test.txt");
            IOUtils.copy(in, out);
            out.flush();
        }
    }
}
