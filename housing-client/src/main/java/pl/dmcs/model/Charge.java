package pl.dmcs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "CHARGE_T")
@AllArgsConstructor
@NoArgsConstructor
public class Charge extends BaseEntity {

    @Column(name = "DATE_TIME")
    private LocalDate date = LocalDate.now();

    @Column(name = "ELECTRICITY")
    private Double electricity;

    @Column(name = "GAS")
    private Double gas;

    @Column(name = "COLD_WATER")
    private Double coldWater;

    @Column(name = "HOT_WATER")
    private Double hotWater;

    @Column(name = "SEWAGE")
    private Double sewage;

    @Column(name = "FUND_RENOVATION")
    private Double foundRenovation;

    @JsonBackReference
    @ManyToOne
    private Local local;
}
