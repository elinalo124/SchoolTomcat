package com.elina.SchoolTomcat.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@NamedQueries({
        @NamedQuery(name = "Teacher.findByName",
                query = "SELECT t FROM Teacher t WHERE (t.firstName = :firstName AND t.lastName = :lastName)"),
        @NamedQuery(name = "Teacher.findByID",
                query = "SELECT t FROM Teacher t WHERE t.id = :id"),
        @NamedQuery(name = "Teacher.findAll",
                query = "SELECT t FROM Teacher t")
})
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Teacher extends User{
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String education;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "course_id")
    private Course course;
    private boolean deletedFlag;

    public Teacher(String firstName, String lastName, String education) {
        super(firstName, lastName);
        this.education = education;
        this.deletedFlag = false;
    }

    @Override
    public String toString() {
        String title = "********TABLE Teacher********";
        String columns = "id        firstName      lastName     education   |";
        String values = id +"        "+ getFirstName() +"    "+ getLastName()+"    "+education;
        return "\n"+title + "\n" + columns + "\n" + values+"\n\n";
    }
}
