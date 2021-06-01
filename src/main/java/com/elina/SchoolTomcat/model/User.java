package com.elina.SchoolTomcat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.cfg.AccessType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    private int id;
    @Column(length = 64, nullable = false)
    private String firstName;
    @Column(length = 64, nullable = false)
    private String lastName;
}
