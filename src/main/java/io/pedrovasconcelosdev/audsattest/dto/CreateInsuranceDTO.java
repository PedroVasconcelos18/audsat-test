package io.pedrovasconcelosdev.audsattest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateInsuranceDTO {

    private Long customerId;
    private Long carId;

}
