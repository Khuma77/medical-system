package com.stohirov.service.impls;

import com.stohirov.entities.VitalValues;
import com.stohirov.mappers.VitalValuesMapper;
import com.stohirov.payload.VitalValuesDTO;
import com.stohirov.repository.VitalValuesRepository;
import com.stohirov.service.VitalValuesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class VitalValuesServiceImpl implements VitalValuesService {

    private final VitalValuesRepository repository;
    private final VitalValuesMapper mapper;

    public VitalValuesServiceImpl(VitalValuesRepository repository, VitalValuesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public VitalValuesDTO create(VitalValuesDTO dto) {
        log.info("Creating VitalValues for recordId: {}", dto.getRecordId());
        VitalValues entity = mapper.toEntity(dto);
        VitalValues saved = repository.save(entity);
        log.info("VitalValues created with id: {}", saved.getId());
        return mapper.toDto(saved);
    }

    @Override
    public VitalValuesDTO getById(Long id) {
        VitalValues entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VitalValues not found with id: " + id));
        return mapper.toDto(entity);
    }

    @Override
    public List<VitalValuesDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    @Override
    public VitalValuesDTO update(Long id, VitalValuesDTO dto) {
        VitalValues entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VitalValues not found with id: " + id));
        entity.setValue(dto.getValue());
        VitalValues updated = repository.save(entity);
        log.info("VitalValues updated with id: {}", updated.getId());
        return mapper.toDto(updated);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        VitalValues entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VitalValues not found with id: " + id));
        repository.delete(entity);
        log.info("VitalValues deleted with id: {}", id);
        return true;
    }
}
