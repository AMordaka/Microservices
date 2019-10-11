package pl.dmcs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "USER_T")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @NotEmpty
    @Column(name = "LOGIN", unique = true, nullable = false)
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    @NotEmpty
    private String password;

    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotEmpty
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @NotEmpty
    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = false;

    @NotEmpty
    @ManyToMany(cascade = {CascadeType.MERGE,
            CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES_T",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST})
    private Set<Local> locals;

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + address;
    }
}
