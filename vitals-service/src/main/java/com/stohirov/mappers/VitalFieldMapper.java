package com.stohirov.mappers;

import com.stohirov.entities.VitalField;
import com.stohirov.payload.VitalFieldDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MeasurementMapper.class})
public interface VitalFieldMapper {
    VitalFieldDTO toDto(VitalField entity);
    VitalField toEntity(VitalFieldDTO dto);
}
