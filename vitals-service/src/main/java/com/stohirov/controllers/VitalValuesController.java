package com.stohirov.controllers;

import com.stohirov.payload.VitalValuesDTO;
import com.stohirov.service.VitalValuesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vital-values")
@RequiredArgsConstructor
public class VitalValuesController {

    private final VitalValuesService vitalValueService;

    @PostMapping
    public VitalValuesDTO create(@RequestBody VitalValuesDTO vitalValueDTO) {
        return vitalValueService.create(vitalValueDTO);
    }

    @GetMapping("/{id}")
    public VitalValuesDTO getById(@PathVariable Long id) {
        return vitalValueService.getById(id);
    }

    @GetMapping
    public List<VitalValuesDTO> getAll() {
        return vitalValueService.getAll();
    }

    @PutMapping("/{id}")
    public VitalValuesDTO update(@PathVariable Long id, @RequestBody VitalValuesDTO vitalValueDTO) {
        return vitalValueService.update(id, vitalValueDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vitalValueService.delete(id);
    }
}
