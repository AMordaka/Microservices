package pl.dmcs.manager.service.managerservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "OCCUPANTS")
@Getter
@Setter
public class Occupant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "occupant")
    private Set<Premises> premises;

}