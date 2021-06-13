package com.elina.SchoolTomcat.model;

import lombok.*;
import org.hibernate.cfg.AccessType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 64, nullable = false)
    private String firstName;
    @Column(length = 64, nullable = false)
    private String lastName;
    private boolean deletedFlag;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.deletedFlag = false;
    }
}
