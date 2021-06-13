package com.elina.SchoolTomcat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Course.findByName",
                query = "SELECT c FROM Course c WHERE c.name = :name"),
        @NamedQuery(name = "Course.findAll",
                query = "SELECT c FROM Course c")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 64, nullable = false)
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;
    @OneToOne(mappedBy = "course")
    private Teacher teacher;
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
    private boolean deletedFlag;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
        this.students = new HashSet<>();
        this.deletedFlag = false;
    }

    @Override
    public String toString() {
        String title = "********TABLE Course********";
        String columns = "id        name      description     |";
        String values = id +"        "+ name +"    "+ description;
        return "\n"+title + "\n" + columns + "\n" + values+"\n\n";
    }
}
