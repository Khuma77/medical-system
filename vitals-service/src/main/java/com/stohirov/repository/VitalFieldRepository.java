package com.stohirov.repository;

import com.stohirov.entities.VitalField;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface VitalFieldRepository extends JpaRepository<VitalField, Long> {
}
