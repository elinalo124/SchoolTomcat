package com.elina.SchoolTomcat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Access(AccessType.FIELD) //persistance at the level of the fields
@NamedQueries({
        @NamedQuery(name = "Student.findByName",
                query = "SELECT s FROM Student s WHERE (s.firstName = :firstName AND s.lastName = :lastName)"),
        @NamedQuery(name = "Student.findByID",
                query = "SELECT s FROM Student s WHERE s.id = :id"),
        @NamedQuery(name = "Student.findAll",
                query = "SELECT s FROM Student s")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student extends User{
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String major;
    @ManyToMany
    @JoinTable(
            name = "STUDENT_COURSES",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnore
    private Set<Course> courses;
    private boolean deletedFlag;


    public Student(String firstName, String lastName, String major) {
        super(firstName, lastName);
        this.major = major;
        this.courses = new HashSet<>();
        this.deletedFlag = false;
    }

    @Override
    public String toString() {
        String title = "********TABLE Studen********";
        String columns = "id        firstName      lastName     major   |";
        String values = id +"        "+ getFirstName() +"    "+ getLastName()+"    "+major;
        return "\n"+title + "\n" + columns + "\n" + values+"\n\n";
    }
}
