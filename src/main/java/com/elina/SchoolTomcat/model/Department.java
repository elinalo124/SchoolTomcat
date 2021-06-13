package com.elina.SchoolTomcat.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


@Entity @Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = "Department.findByName",
                query = "SELECT d FROM Department d WHERE d.name = :name"),
        @NamedQuery(name = "Department.findAll",
                query = "SELECT d FROM Department d")
})
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department {
    @Id
    @EqualsAndHashCode.Include
    //@SequenceGenerator(name="seq1",sequenceName="HIB_SEQ")
    //@GeneratedValue(strategy=SEQUENCE,generator="seq1")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 64, nullable = false)
    private String name;
    @OneToMany(mappedBy = "department")
    private List<Course> courses = new LinkedList<>();
    private boolean deletedFlag;

    public Department(String name) {
        this.name = name;
        this.courses = new LinkedList<>();
        this.deletedFlag = false;
    }

    @Override
    public String toString()
    {
        String title = "********TABLE Department********";
        String columns = "id        name      courses     |";
        String values = id +"        "+ name +"    "+ courses.toString();
        return "\n"+title + "\n" + columns + "\n" + values+"\n\n";
    }
}
