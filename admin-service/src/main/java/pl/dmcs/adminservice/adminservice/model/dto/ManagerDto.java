package pl.dmcs.adminservice.adminservice.model.dto;

import lombok.Data;

@Data
public class ManagerDto {
    private String firstName;
    private String lastName;
    private Integer active;
    private String email;
}
