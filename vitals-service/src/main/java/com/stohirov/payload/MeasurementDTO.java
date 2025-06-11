package com.stohirov.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {
    private Long id;
    private String name;
    private String symbol;
    private String description;
}
