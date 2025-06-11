package com.stohirov.entities;

import com.stohirov.entities.template.AbsEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "vital_records")
@SQLDelete(sql = "UPDATE vital_records SET is_deleted=true WHERE id = ?")
@SQLRestriction(value = "is_deleted=false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VitalRecord extends AbsEntity {

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private Timestamp recordedAt;

    @Column(columnDefinition = "text")
    private String notes;

}
