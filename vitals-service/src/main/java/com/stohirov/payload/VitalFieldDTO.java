package com.stohirov.payload;

import com.stohirov.entities.enums.FieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VitalFieldDTO {
    private Long id;
    private String name;
    private MeasurementDTO measurement;
    private FieldType fieldType;
    private boolean isRequired;
}
