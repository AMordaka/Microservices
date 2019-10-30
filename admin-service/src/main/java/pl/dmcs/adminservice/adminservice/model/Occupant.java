package pl.dmcs.adminservice.adminservice.model;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "occupant")
    private Set<Premises> premises;

}