package com.stohirov.entities;

import com.stohirov.entities.enums.FieldType;
import com.stohirov.entities.template.AbsEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "vital_fields")
@SQLDelete(sql = "UPDATE vital_fields SET is_deleted=true WHERE id = ?")
@SQLRestriction(value = "is_deleted=false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VitalField extends AbsEntity {

    @Column(nullable = false, length = 72)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id", nullable = false)
    private Measurement measurement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private FieldType fieldType;

    @Column(nullable = false)
    private boolean isRequired = false;
}
