package com.stohirov.service.impls;

import com.stohirov.entities.VitalRecord;
import com.stohirov.mappers.VitalRecordMapper;
import com.stohirov.payload.VitalRecordDTO;
import com.stohirov.repository.VitalRecordRepository;
import com.stohirov.service.VitalRecordService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class VitalRecordServiceImpl implements VitalRecordService {

    private final VitalRecordRepository repository;
    private final VitalRecordMapper mapper;

    public VitalRecordServiceImpl(VitalRecordRepository repository, VitalRecordMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public VitalRecordDTO create(VitalRecordDTO dto) {
        log.info("Creating VitalRecord for user: {}", dto.getUserId());
        VitalRecord record = mapper.toEntity(dto);
        VitalRecord saved = repository.save(record);
        log.info("VitalRecord created with id: {}", saved.getId());
        return mapper.toDto(saved);
    }

    @Override
    public VitalRecordDTO getById(Long id) {
        VitalRecord record = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VitalRecord not found with id: " + id));
        return mapper.toDto(record);
    }

    @Override
    public List<VitalRecordDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    @Override
    public VitalRecordDTO update(Long id, VitalRecordDTO dto) {
        VitalRecord existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VitalRecord not found with id: " + id));
        existing.setRecordedAt(dto.getRecordedAt());
        existing.setNotes(dto.getNotes());
        VitalRecord updated = repository.save(existing);
        log.info("VitalRecord updated with id: {}", updated.getId());
        return mapper.toDto(updated);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        VitalRecord record = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VitalRecord not found with id: " + id));
        repository.delete(record);
        log.info("VitalRecord deleted with id: {}", id);
        return true;
    }
}
