package com.example.oauth2backend.repo;

import com.example.oauth2backend.entity.FormDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepo extends JpaRepository<FormDataEntity,Long> {
}
