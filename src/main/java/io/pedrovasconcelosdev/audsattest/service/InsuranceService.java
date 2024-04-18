package io.pedrovasconcelosdev.audsattest.service;

import io.pedrovasconcelosdev.audsattest.domain.Insurances;
import io.pedrovasconcelosdev.audsattest.dto.CreateInsuranceDTO;
import io.pedrovasconcelosdev.audsattest.dto.InsuranceDTO;
import io.pedrovasconcelosdev.audsattest.dto.InsuranceUpdatedDTO;
import io.pedrovasconcelosdev.audsattest.dto.UpdateInsuranceDTO;

public interface InsuranceService {

    Insurances createInsurance(CreateInsuranceDTO createInsuranceDTO);

    InsuranceDTO getInsuranceById(Long id);

    InsuranceUpdatedDTO editInsurance(Long id, UpdateInsuranceDTO updateInsuranceDTO);

    void deleteInsurance(Long id);
}
