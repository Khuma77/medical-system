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

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "diagnosis")
@EntityListeners(AuditingEntityListener.class)
@SQLRestriction(value = "is_deleted=false")
@SQLDelete(sql = "update diagnosis set is_deleted=true where id = ?")
public class Diagnosis extends AbsEntity {

    @ManyToMany
    @JoinTable(
            name = "diagnosis_diseases",
            joinColumns = @JoinColumn(name = "diagnosis_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<Disease> diseases;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "doctor_id")
    private UUID doctorId;

}
