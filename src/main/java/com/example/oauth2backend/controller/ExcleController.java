package com.example.oauth2backend.controller;

import com.example.oauth2backend.utill.excle.DbToExcleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExcleController {
    private final DbToExcleService dbToExcleService;
    @GetMapping("/downloadExcleFile")
    public void downLoadExcleFile(HttpServletResponse response) throws IOException {
        var data=dbToExcleService.getAllFormData();
        ByteArrayInputStream byteArrayInputStream=dbToExcleService.export(data);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment:filename=contents.xls");
        IOUtils.copy(byteArrayInputStream,response.getOutputStream());
    }
}
