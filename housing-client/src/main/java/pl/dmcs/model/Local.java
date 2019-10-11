package pl.dmcs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "LOCAL_T")
@AllArgsConstructor
@NoArgsConstructor
public class Local extends BaseEntity {

    @NotEmpty
    @Column(name = "LOCAL_NUMBER", unique = true, nullable = false)
    private String number;

    @Column(name = "IS_HOUSING", nullable = false)
    private Boolean isHousing;

    @Column(name = "IS_RENTED")
    private Boolean isRented = false;

    @Column(name = "SURFACE_AREA", nullable = false)
    private String surfaceArea;

    @ManyToOne
    private Building building;

    @Column(name = "IS_FILLED")
    private Boolean isChargesFilled = false;

    @Column(name = "CAN_FILL_CHARGES")
    private Boolean canFillCharges = true;

    @Column(name = "IS_ACCEPTED")
    private Boolean isChargesAccepted = false;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private Set<Charge> charges = new HashSet<>();

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return building + " - " + number + " | " + surfaceArea + " m^2";
    }
}
