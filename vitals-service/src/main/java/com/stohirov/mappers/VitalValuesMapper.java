package com.stohirov.mappers;

import com.stohirov.entities.VitalValues;
import com.stohirov.payload.VitalValuesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VitalValuesMapper {

    @Mapping(source = "record.id", target = "recordId")
    @Mapping(source = "vitalField.id", target = "fieldId")
    VitalValuesDTO toDto(VitalValues vitalValues);

    @Mapping(source = "recordId", target = "record.id")
    @Mapping(source = "fieldId", target = "vitalField.id")
    VitalValues toEntity(VitalValuesDTO vitalValueDTO);
}
