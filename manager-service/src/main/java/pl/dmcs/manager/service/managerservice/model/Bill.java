package pl.dmcs.manager.service.managerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Bill implements Comparable<Bill> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ELECTRICITY")
    private double electricity;

    @Column(name = "GAS")
    private double gas;

    @Column(name = "COLD_WATER")
    private double coldWater;

    @Column(name = "HOT_WATER")
    private double hotWater;

    @Column(name = "HEATING")
    private double heating;

    @Column(name = "DATE")
    private String date;

    @Column(name = "ACCEPTED")
    private boolean accepted;

    @Column(name = "DONE")
    private boolean done;


    @ManyToOne
    @JoinColumn(name = "premises_id")
    @JsonIgnore
    private Premises premises;


    @Override
    public int compareTo(Bill o) {
        return this.getId().compareTo(o.getId());
    }
}
