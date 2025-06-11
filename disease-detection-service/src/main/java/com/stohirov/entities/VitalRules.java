package com.stohirov.entities;

import com.stohirov.entities.template.AbsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "vital_rules")
@SQLDelete(sql = "update vital_rules set is_deleted=true where id = ?")
@SQLRestriction(value = "is_deleted=false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VitalRules extends AbsEntity {

    @Column(nullable = false)
    private Long vitalRecordId;

    @Column(nullable = false)
    private String operator;

    @Column(nullable = false)
    private Long thresholdMax;

    @Column(nullable = false)
    private Long thresholdMin;

    @Column(nullable = false)
    private Long diseaseId;

    @Column(columnDefinition = "TEXT")
    private String description;

}
