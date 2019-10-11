package pl.dmcs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "BUILDING_T")
@AllArgsConstructor
@NoArgsConstructor
public class Building extends BaseEntity {

    private String address;

    @OneToMany(fetch= FetchType.LAZY, mappedBy="building", cascade = {CascadeType.PERSIST})
    private Set<Local> locals;

    @Override
    public String toString() {
        return this.address;
    }
}
