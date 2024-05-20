package com.example.oauth2backend.excel1.validation;

import com.example.oauth2backend.excel1.ExportToExcel;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
@RestController
@RequiredArgsConstructor
public class FormatDownController {
    private final ExcalFormat excalFormat;
    @GetMapping("/downloadFormat")
    public void downloadCustomerExcleFile(HttpServletResponse response) throws IOException {
        ByteArrayInputStream byteArrayInputStream=excalFormat.formatDownload();
        response.setContentType("application/vnd.ms-excel");
        IOUtils.copy(byteArrayInputStream,response.getOutputStream());
    }
}
