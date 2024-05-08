package com.example.oauth2backend.repo;

import com.example.oauth2backend.entity.FormDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepo extends JpaRepository<FormDataEntity,Long> {

     List<FormDataEntity> findAllByEmail(String email);
}
