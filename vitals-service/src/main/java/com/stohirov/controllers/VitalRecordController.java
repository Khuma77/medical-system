package com.stohirov.controllers;

import com.stohirov.payload.VitalRecordDTO;
import com.stohirov.service.VitalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vital-records")
@RequiredArgsConstructor
public class VitalRecordController {

    private final VitalRecordService vitalRecordService;

    @PostMapping
    public VitalRecordDTO create(@RequestBody VitalRecordDTO vitalRecordDTO) {
        return vitalRecordService.create(vitalRecordDTO);
    }

    @GetMapping("/{id}")
    public VitalRecordDTO getById(@PathVariable Long id) {
        return vitalRecordService.getById(id);
    }

    @GetMapping
    public List<VitalRecordDTO> getAll() {
        return vitalRecordService.getAll();
    }

    @PutMapping("/{id}")
    public VitalRecordDTO update(@PathVariable Long id, @RequestBody VitalRecordDTO vitalRecordDTO) {
        return vitalRecordService.update(id, vitalRecordDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vitalRecordService.delete(id);
    }
}
