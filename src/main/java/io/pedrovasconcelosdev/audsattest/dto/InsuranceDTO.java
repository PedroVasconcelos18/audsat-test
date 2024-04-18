package io.pedrovasconcelosdev.audsattest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsuranceDTO {

    private Long customerId;
    private String customerName;
    private Long carId;
    private String carModel;
    private String carValue;
    private String budget;
    private String fipePercentage;
    private Boolean isActive;

}
