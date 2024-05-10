package com.example.oauth2backend.excel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataEntityRepository extends JpaRepository<DataItemEntity,Integer> {
}
