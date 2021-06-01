package com.elina.SchoolTomcat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
public class Department {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Course> courses = new LinkedList<>();

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString()
    {
        String title = "********TABLE Departments********";
        String columns = "id        name      courses     |";
        String values = id +"        "+ name +"    "+ courses.toString();
        return "\n"+title + "\n" + columns + "\n" + values+"\n\n";
    }
}
