package com.stohirov.service.impls;

import com.stohirov.entities.Measurement;
import com.stohirov.entities.VitalField;
import com.stohirov.mappers.VitalFieldMapper;
import com.stohirov.payload.VitalFieldDTO;
import com.stohirov.repository.VitalFieldRepository;
import com.stohirov.service.MeasurementService;
import com.stohirov.service.VitalFieldService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class VitalFieldServiceImpl implements VitalFieldService {

    private final VitalFieldRepository repository;
    private final VitalFieldMapper mapper;
    private final MeasurementService measurementService;

    public VitalFieldServiceImpl(
            VitalFieldRepository repository,
            VitalFieldMapper mapper,
            MeasurementService measurementService) {
        this.repository = repository;
        this.mapper = mapper;
        this.measurementService = measurementService;
    }

    @Transactional
    @Override
    public VitalFieldDTO create(VitalFieldDTO dto) {
        log.info("Creating VitalField: {}", dto.getName());

        Measurement measurement = measurementService.getById(dto.getMeasurement().getId());

        VitalField entity = mapper.toEntity(dto);
        entity.setMeasurement(measurement);

        VitalField saved = repository.save(entity);
        log.info("VitalField created with id: {}", saved.getId());
        return mapper.toDto(saved);
    }

    @Override
    public VitalFieldDTO getById(Long id) {
        log.info("Fetching VitalField with id: {}", id);
        VitalField entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VitalField not found with id: " + id));
        return mapper.toDto(entity);
    }

    @Override
    public List<VitalFieldDTO> getAll() {
        log.info("Fetching all VitalFields");
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    @Override
    public VitalFieldDTO update(Long id, VitalFieldDTO dto) {
        log.info("Updating VitalField with id: {}", id);

        VitalField existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VitalField not found with id: " + id));

        Measurement measurement = measurementService.getById(dto.getMeasurement().getId());

        existing.setName(dto.getName());
        existing.setMeasurement(measurement);
        existing.setFieldType(dto.getFieldType());
        existing.setRequired(dto.isRequired());

        VitalField updated = repository.save(existing);
        log.info("VitalField updated with id: {}", updated.getId());
        return mapper.toDto(updated);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        log.info("Deleting VitalField with id: {}", id);
        VitalField entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VitalField not found with id: " + id));
        repository.delete(entity);
        log.info("VitalField deleted with id: {}", id);
        return true;
    }
}
