package com.stohirov.controllers;

import com.stohirov.payload.MeasurementDTO;
import com.stohirov.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/measurements")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping
    public MeasurementDTO create(@RequestBody MeasurementDTO measurementDTO) {
        return measurementService.create(measurementDTO);
    }

    @GetMapping("/{id}")
    public MeasurementDTO getById(@PathVariable Long id) {
        return measurementService.getById(id);
    }

    @GetMapping
    public List<MeasurementDTO> getAll() {
        return measurementService.getAll();
    }

    @PutMapping("/{id}")
    public MeasurementDTO update(@PathVariable Long id, @RequestBody MeasurementDTO measurementDTO) {
        return measurementService.update(id, measurementDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        measurementService.delete(id);
    }
}
