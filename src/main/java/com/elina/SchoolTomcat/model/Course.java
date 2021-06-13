package com.elina.SchoolTomcat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

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
public class Course {
    @Id
    private int id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "department")
    @JsonIgnore
    private Department department;
    @OneToOne(mappedBy = "course",cascade = CascadeType.ALL, orphanRemoval = true)
    private Teacher teacher;
    @ManyToMany(mappedBy = "courses",cascade = CascadeType.ALL)
    private List<Student> students;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Course(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
    }

    @Override
    public String toString() {

        return id +name + description+"\n";
    }
}
