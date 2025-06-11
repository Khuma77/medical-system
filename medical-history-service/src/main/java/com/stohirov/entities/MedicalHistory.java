package com.stohirov.entities;

import com.stohirov.entities.template.AbsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medical_history")
@EntityListeners(AuditingEntityListener.class)
@SQLRestriction(value = "is_deleted=false")
@SQLDelete(sql = "update medical_history set is_deleted=true where id = ?")
public class MedicalHistory extends AbsEntity {

    @ManyToOne
    @JoinColumn(name = "diagnosis_id", nullable = false)
    private Diagnosis diagnosis;

    private Timestamp surgeryDate;

    private Timestamp dischargeDate;

    private String notes;

}
