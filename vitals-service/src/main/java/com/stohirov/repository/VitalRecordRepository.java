package com.stohirov.repository;

import com.stohirov.entities.VitalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitalRecordRepository extends JpaRepository<VitalRecord, Long> {
}
