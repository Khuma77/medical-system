package com.stohirov.mappers;

import com.stohirov.entities.VitalRecord;
import com.stohirov.payload.VitalRecordDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VitalRecordMapper {
    VitalRecordDTO toDto(VitalRecord vitalRecord);
    VitalRecord toEntity(VitalRecordDTO vitalRecordDTO);
}
