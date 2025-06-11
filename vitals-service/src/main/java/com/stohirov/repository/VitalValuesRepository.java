package com.stohirov.repository;

import com.stohirov.entities.VitalValues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitalValuesRepository extends JpaRepository<VitalValues, Long> {
}
