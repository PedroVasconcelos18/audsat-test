package io.pedrovasconcelosdev.audsattest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateInsuranceDTO {

    private Long customerId;
    private Long carId;
    private Boolean isActive;

}
