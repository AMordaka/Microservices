package pl.dmcs.manager.service.managerservice.model.dto;

import lombok.Data;

@Data
public class OccupantDetailsDto {
    private String firstName;
    private String lastName;
    private Integer active;
    private String email;
    private String pesel;
    private String accountNumber;
}
