package com.stohirov.service;

import com.stohirov.payload.VitalValuesDTO;

import java.util.List;

public interface VitalValuesService {
    VitalValuesDTO create(VitalValuesDTO dto);
    VitalValuesDTO getById(Long id);
    List<VitalValuesDTO> getAll();
    VitalValuesDTO update(Long id, VitalValuesDTO dto);
    boolean delete(Long id);
}
