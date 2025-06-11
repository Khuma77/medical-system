package com.stohirov.service.impls;

import com.stohirov.entities.Measurement;
import com.stohirov.mappers.MeasurementMapper;
import com.stohirov.payload.MeasurementDTO;
import com.stohirov.repository.MeasurementRepository;
import com.stohirov.service.MeasurementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository, MeasurementMapper measurementMapper) {
        this.measurementRepository = measurementRepository;
        this.measurementMapper = measurementMapper;
    }

    @Transactional
    @Override
    public MeasurementDTO create(MeasurementDTO dto) {
        log.info("Creating measurement: {}", dto.getName());
        Measurement measurement = measurementMapper.toEntity(dto);
        Measurement saved = measurementRepository.save(measurement);
        log.info("Measurement created with id: {}", saved.getId());
        return measurementMapper.toDto(saved);
    }

    @Override
    public MeasurementDTO getById(Long id) {
        log.info("Fetching measurement with id: {}", id);
        Measurement measurement = measurementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Measurement not found with id: " + id));
        return measurementMapper.toDto(measurement);
    }

    @Override
    public List<MeasurementDTO> getAll() {
        log.info("Fetching all measurements");
        return measurementRepository.findAll().stream()
                .map(measurementMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public MeasurementDTO update(Long id, MeasurementDTO dto) {
        log.info("Updating measurement with id: {}", id);

        Measurement measurement = measurementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Measurement not found with id: " + id));

        measurement.setName(dto.getName());
        measurement.setSymbol(dto.getSymbol());
        measurement.setDescription(dto.getDescription());

        Measurement updated = measurementRepository.save(measurement);
        log.info("Measurement updated with id: {}", updated.getId());
        return measurementMapper.toDto(updated);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        log.info("Deleting measurement with id: {}", id);

        Measurement measurement = measurementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Measurement not found with id: " + id));

        measurementRepository.delete(measurement);
        log.info("Measurement deleted with id: {}", id);
        return true;
    }
}
