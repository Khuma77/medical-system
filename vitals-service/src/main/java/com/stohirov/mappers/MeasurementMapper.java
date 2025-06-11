package com.stohirov.mappers;

import com.stohirov.entities.Measurement;
import com.stohirov.payload.MeasurementDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {
    MeasurementDTO toDto(Measurement entity);
    Measurement toEntity(MeasurementDTO dto);
}
