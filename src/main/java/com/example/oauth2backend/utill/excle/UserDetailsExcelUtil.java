package com.example.oauth2backend.utill.excle;

import com.example.oauth2backend.dto.UserRequestDto;
import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsExcelUtil implements UserDetailDbtoMySQL {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Override
    public ByteArrayInputStream exportUserDB(List<UserRequestDto> userRequestDtoList) {
        try (Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("User Details");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("ID");
            cell = row.createCell(1);
            cell.setCellValue("First Name");
            cell = row.createCell(2);
            cell.setCellValue("Email");
            cell = row.createCell(4);
            cell.setCellValue("Password");
            cell = row.createCell(5);
            cell.setCellValue("Picture");
            for(int i=0;i<userRequestDtoList.size();i++){
                Row dataRow = sheet.createRow(i+1);
                dataRow.createCell(0).setCellValue(userRequestDtoList.get(i).getId());
                dataRow.createCell(1).setCellValue(userRequestDtoList.get(i).getFirstName());
                dataRow.createCell(2).setCellValue(userRequestDtoList.get(i).getEmail());
                dataRow.createCell(4).setCellValue(userRequestDtoList.get(i).getPhoneNumber());
                dataRow.createCell(5).setCellValue(userRequestDtoList.get(i).getPicture());

            }
            for (int i = 0; i < userRequestDtoList.size(); i++) {
                sheet.autoSizeColumn(i);
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserRequestDto> getAllUserDetails() {
        List<UserEntity> list=userRepository.findAll();
        return list.stream()
                .map(li->modelMapper.map(li,UserRequestDto.class))
                .collect(Collectors.toUnmodifiableList());
    }
}
