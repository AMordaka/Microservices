package pl.dmcs.occupant.service.occupantservice.model.dto;

import lombok.Data;

@Data
public class BillDto {
    private Double electricity;
    private Double gas;
    private Double coldWater;
    private Double hotWater;
    private Double heating;
    private String date;
    private Boolean accepted;
    private Boolean done;
}
