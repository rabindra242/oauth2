package com.example.oauth2backend.excel;

import com.example.oauth2backend.entity.FormDataEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public class FormatCheckerHelper {

    public static boolean checkContentType(MultipartFile file){
        var contentType=file.getContentType();
        if(contentType.equals("application/vnd.ms-excel")){
            return true;
        }else {
            return false;
        }
    }

//    public List<FormDataEntity> convertExcleToList(InputStream s){
//    }


}
