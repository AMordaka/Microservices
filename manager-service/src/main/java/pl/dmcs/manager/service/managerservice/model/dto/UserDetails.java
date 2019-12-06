package pl.dmcs.manager.service.managerservice.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDetails {
    private int id;
    private int userId;
    private String pesel;
    private String accountNumber;
}
