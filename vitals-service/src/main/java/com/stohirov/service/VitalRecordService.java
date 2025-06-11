package com.stohirov.service;

import com.stohirov.payload.VitalRecordDTO;

import java.util.List;

public interface VitalRecordService {
    VitalRecordDTO create(VitalRecordDTO dto);
    VitalRecordDTO getById(Long id);
    List<VitalRecordDTO> getAll();
    VitalRecordDTO update(Long id, VitalRecordDTO dto);
    boolean delete(Long id);
}
