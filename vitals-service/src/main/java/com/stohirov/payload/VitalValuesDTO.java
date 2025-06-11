package com.stohirov.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VitalValuesDTO {
    private Long id;
    private Long recordId;
    private Long fieldId;
    private String value;
}
