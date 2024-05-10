package com.example.oauth2backend.controller;

import com.example.oauth2backend.utill.excle.DbToExcleService;
import com.example.oauth2backend.utill.excle.UserDetailsExcelUtil;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
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
    private final UserDetailsExcelUtil userDetailsExcelUtil;
    @GetMapping("/downloadExcelFile")
    public void downLoadExcleFile(HttpServletResponse response) throws IOException {
        var data=dbToExcleService.getAllFormData();
        ByteArrayInputStream byteArrayInputStream=dbToExcleService.export(data);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment:filename=contents.xls");
        IOUtils.copy(byteArrayInputStream,response.getOutputStream());
    }
    @GetMapping("/downloadUserExcelFile")
    public void downLoadUserExcleFile(HttpServletResponse response) throws IOException {
        var data=userDetailsExcelUtil.getAllUserDetails();
        ByteArrayInputStream byteArrayInputStream=userDetailsExcelUtil.exportUserDB(data);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment:filename=contents.xls");
        IOUtils.copy(byteArrayInputStream,response.getOutputStream());
    }
}
