package com.stohirov.entities;

import com.stohirov.entities.template.AbsEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "measurement")
@SQLDelete(sql = "UPDATE measurement SET is_deleted=true WHERE id = ?")
@SQLRestriction(value = "is_deleted=false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Measurement extends AbsEntity {

    @Column(nullable = false, length = 72)
    private String name;

    @Column(length = 32)
    private String symbol;

    @Column(columnDefinition = "TEXT")
    private String description;
}
