package com.stohirov.service;

import com.stohirov.payload.VitalFieldDTO;

import java.util.List;

public interface VitalFieldService {
    VitalFieldDTO create(VitalFieldDTO dto);
    VitalFieldDTO getById(Long id);
    List<VitalFieldDTO> getAll();
    VitalFieldDTO update(Long id, VitalFieldDTO dto);
    boolean delete(Long id);
}
