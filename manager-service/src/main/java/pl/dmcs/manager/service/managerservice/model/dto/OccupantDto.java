package pl.dmcs.manager.service.managerservice.model.dto;

import lombok.Data;

@Data
public class OccupantDto {
    private String firstName;
    private String lastName;
    private Integer active;
    private String email;
}
