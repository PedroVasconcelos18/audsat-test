package io.pedrovasconcelosdev.audsattest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InsuranceUpdatedDTO {

    private Long customerId;
    private String customerName;
    private Long carId;
    private String carModel;
    private Boolean isActive;

}
