package com.stohirov.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VitalRecordDTO {
    private Long id;
    private UUID userId;
    private Timestamp recordedAt;
    private String notes;
}
