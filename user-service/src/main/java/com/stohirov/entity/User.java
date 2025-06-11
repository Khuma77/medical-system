package com.stohirov.entity;

import com.stohirov.entity.enums.Gender;
import com.stohirov.entity.templates.AbsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@SQLRestriction(value = "is_deleted=false")
@SQLDelete(sql = "update users set is_deleted=true where id = ?")
public class User extends AbsEntity {

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @Column(nullable = false)
    private Timestamp birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

}
