package io.pedrovasconcelosdev.audsattest.service.impl;

import io.pedrovasconcelosdev.audsattest.domain.Cars;
import io.pedrovasconcelosdev.audsattest.domain.Claims;
import io.pedrovasconcelosdev.audsattest.domain.Customer;
import io.pedrovasconcelosdev.audsattest.domain.Insurances;
import io.pedrovasconcelosdev.audsattest.dto.CreateInsuranceDTO;
import io.pedrovasconcelosdev.audsattest.dto.InsuranceDTO;
import io.pedrovasconcelosdev.audsattest.dto.InsuranceUpdatedDTO;
import io.pedrovasconcelosdev.audsattest.dto.UpdateInsuranceDTO;
import io.pedrovasconcelosdev.audsattest.repository.*;
import io.pedrovasconcelosdev.audsattest.service.InsuranceService;
import io.pedrovasconcelosdev.audsattest.utils.CustomerUtils;
import io.pedrovasconcelosdev.audsattest.utils.NumberUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final CustomerRepository customerRepository;
    private final CarsRepository carsRepository;
    private final ClaimsRepository claimsRepository;

    @Override
    public Insurances createInsurance(final CreateInsuranceDTO createInsuranceDTO) {
        Customer customer = customerRepository.findById(createInsuranceDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não existe na base de dados."));

        Cars car = carsRepository.findById(createInsuranceDTO.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Carro não existe na base de dados"));

        Insurances insurance = Insurances.builder()
                .customer(customer)
                .cars(car)
                .isActive(true)
                .build();

        return insuranceRepository.save(insurance);
    }

    @Override
    public InsuranceDTO getInsuranceById(final Long id) {
        Insurances insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seguro não existe na base de dados."));

        Customer customer = insurance.getCustomer();
        Cars car = insurance.getCars();

        int finalBudgetPercentage = validateInsurance(customer, car);
        double budget = calculateInsuranceValue(car.getFipeValue(), finalBudgetPercentage);
        String formattedBudget = NumberUtils.formatToTwoDecimalPlaces(budget);
        String formattedCarValue = NumberUtils.formatToTwoDecimalPlaces(car.getFipeValue());

        return InsuranceDTO.builder()
                .carId(car.getId())
                .customerId(customer.getId())
                .carModel(car.getModel())
                .customerName(customer.getName())
                .isActive(insurance.getIsActive())
                .carValue("R$ " + formattedCarValue)
                .fipePercentage(finalBudgetPercentage + "%")
                .budget("R$ " + formattedBudget)
                .build();
    }

    @Override
    public InsuranceUpdatedDTO editInsurance(final Long id, final UpdateInsuranceDTO updateInsuranceDTO) {
        Insurances insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seguro não existe na base de dados."));

        updateCustomer(insurance, updateInsuranceDTO.getCustomerId());
        updateCar(insurance, updateInsuranceDTO.getCarId());
        updateIsActive(insurance, updateInsuranceDTO.getIsActive());

        insuranceRepository.save(insurance);

        return InsuranceUpdatedDTO.builder()
                .customerId(insurance.getCustomer().getId())
                .customerName(insurance.getCustomer().getName())
                .carId(insurance.getCars().getId())
                .carModel(insurance.getCars().getModel())
                .isActive(insurance.getIsActive())
                .build();
    }

    @Override
    public void deleteInsurance(Long id) {
        Insurances insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seguro não existe na base de dados."));

        insuranceRepository.delete(insurance);
    }

    private void updateCustomer(Insurances insurance, Long customerId) {
        if (customerId != null) {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não existe na base de dados."));
            insurance.setCustomer(customer);
        }
    }

    private void updateCar(Insurances insurance, Long carId) {
        if (carId != null) {
            Cars car = carsRepository.findById(carId)
                    .orElseThrow(() -> new EntityNotFoundException("Carro não existe na base de dados."));
            insurance.setCars(car);
        }
    }

    private void updateIsActive(Insurances insurance, Boolean isActive) {
        if (isActive != null) {
            insurance.setIsActive(isActive);
        }
    }

    private int validateInsurance(Customer customer, Cars car) {
        int finalBudgetPercentage = 6;

        int customerAge = CustomerUtils.getCustomerAge(customer);
        boolean isBetween18And25 = CustomerUtils.isBetween(customerAge, 18, 25);

        if (isBetween18And25) {
            finalBudgetPercentage += 2;
        }

        Optional<Claims> carClaims = claimsRepository.findByCars(car);

        if (carClaims.isPresent()){
            finalBudgetPercentage += 2;
        }

        Optional<Claims> driverClaims = claimsRepository.findByDrivers(customer.getDrivers());

        if (driverClaims.isPresent()) {
            finalBudgetPercentage += 2;
        }

        return finalBudgetPercentage;
    }

    private double calculateInsuranceValue(double fipeValue, int percentage) {
        return (fipeValue * percentage) / 100;
    }

}
