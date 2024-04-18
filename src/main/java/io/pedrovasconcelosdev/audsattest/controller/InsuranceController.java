package io.pedrovasconcelosdev.audsattest.controller;

import io.pedrovasconcelosdev.audsattest.domain.Insurances;
import io.pedrovasconcelosdev.audsattest.dto.CreateInsuranceDTO;
import io.pedrovasconcelosdev.audsattest.dto.InsuranceDTO;
import io.pedrovasconcelosdev.audsattest.dto.InsuranceUpdatedDTO;
import io.pedrovasconcelosdev.audsattest.dto.UpdateInsuranceDTO;
import io.pedrovasconcelosdev.audsattest.service.InsuranceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/insurance/budget")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping
    public ResponseEntity<Void> createInsurance(@Valid @RequestBody CreateInsuranceDTO createInsuranceDTO,
                                                UriComponentsBuilder uriComponentsBuilder) {
        Insurances insurance = insuranceService.createInsurance(createInsuranceDTO);
        UriComponents uriComponents =
                uriComponentsBuilder.path("/insurance/budget/{id}")
                .buildAndExpand(insurance.getId());
        String locationUri = uriComponents.toUriString();
        return ResponseEntity.created(URI.create(locationUri)).build();
    }

    @GetMapping("/{id}")
    public InsuranceDTO getInsuranceById(@PathVariable Long id) {
         return insuranceService.getInsuranceById(id);
    }

    @PutMapping("/{id}")
    public InsuranceUpdatedDTO editInsurance(@PathVariable Long id,
                                             @RequestBody UpdateInsuranceDTO updateInsuranceDTO) {
        return insuranceService.editInsurance(id, updateInsuranceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInsurance(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
    }

}
