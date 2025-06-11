package com.stohirov.entities;

import com.stohirov.entities.template.AbsEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "vital_values")
@SQLDelete(sql = "UPDATE vital_values SET is_deleted=true WHERE id = ?")
@SQLRestriction(value = "is_deleted=false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VitalValues extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", nullable = false)
    private VitalRecord record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", nullable = false)
    private VitalField vitalField;

    @Column(nullable = false, length = 255)
    private String value;
}
