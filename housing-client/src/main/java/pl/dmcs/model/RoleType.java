package pl.dmcs.model;

import lombok.Getter;

@Getter
public enum RoleType {
    USER("USER"),
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE");

    String role;

    private RoleType(String roleType) {
        this.role = roleType;
    }
}
