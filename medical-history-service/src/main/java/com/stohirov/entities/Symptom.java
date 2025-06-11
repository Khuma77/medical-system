package com.stohirov.entities;

import com.stohirov.entities.template.AbsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "symptoms")
@EntityListeners(AuditingEntityListener.class)
@SQLRestriction(value = "is_deleted=false")
@SQLDelete(sql = "update symptoms set is_deleted=true where id = ?")
public class Symptom extends AbsEntity {

    @Column(nullable = false)
    private String name;

    private String description;

}
