package com.elina.SchoolTomcat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD) //persistance at the level of the fields
@NamedQueries({
        @NamedQuery(name = "Student.findByID",
                query = "SELECT s FROM Student s WHERE s.id = :id"),
        @NamedQuery(name = "Student.findAll",
                query = "SELECT s FROM Student s")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User{
    @Id
    private int id;
    private String major;
    @ManyToMany
    @JoinTable(
            name = "STUDENT_COURSES",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private List<Course> courses;


    public Student(int id, String firstName, String lastName, int id1, String major) {
        super(id, firstName, lastName);
        this.id = id1;
        this.major = major;
        this.courses = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Student(id=" + id +
                ", major=" + major +
                ", courses=" + courses.toString() + ")";
    }
}
