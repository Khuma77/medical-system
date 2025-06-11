package com.stohirov.controllers;

import com.stohirov.payload.VitalFieldDTO;
import com.stohirov.service.VitalFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vital-fields")
@RequiredArgsConstructor
public class VitalFieldController {

    private final VitalFieldService vitalFieldService;

    @PostMapping
    public VitalFieldDTO create(@RequestBody VitalFieldDTO vitalFieldDTO) {
        return vitalFieldService.create(vitalFieldDTO);
    }

    @GetMapping("/{id}")
    public VitalFieldDTO getById(@PathVariable Long id) {
        return vitalFieldService.getById(id);
    }

    @GetMapping
    public List<VitalFieldDTO> getAll() {
        return vitalFieldService.getAll();
    }

    @PutMapping("/{id}")
    public VitalFieldDTO update(@PathVariable Long id, @RequestBody VitalFieldDTO vitalFieldDTO) {
        return vitalFieldService.update(id, vitalFieldDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vitalFieldService.delete(id);
    }
}
