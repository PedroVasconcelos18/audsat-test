package io.pedrovasconcelosdev.audsattest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateInsuranceDTO {

    @NotNull(message = "É obrigatório informar o id do cliente")
    private Long customerId;

    @NotNull(message = "É obrigatório informar o id do carro")
    private Long carId;

}
