package com.viratrigger.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viratrigger.api.dto.ExcelRecord;

@Repository
public interface ExcelRecordRepository extends JpaRepository<ExcelRecord, Integer> {
}