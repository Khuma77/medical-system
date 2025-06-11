package com.stohirov.service;

import com.stohirov.payload.MeasurementDTO;

import java.util.List;

public interface MeasurementService {
    MeasurementDTO create(MeasurementDTO dto);
    MeasurementDTO getById(Long id);
    List<MeasurementDTO> getAll();
    MeasurementDTO update(Long id, MeasurementDTO dto);
    boolean delete(Long id);
}
