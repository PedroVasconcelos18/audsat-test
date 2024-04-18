package io.pedrovasconcelosdev.audsattest;

import io.pedrovasconcelosdev.audsattest.domain.*;
import io.pedrovasconcelosdev.audsattest.dto.CreateInsuranceDTO;
import io.pedrovasconcelosdev.audsattest.dto.UpdateInsuranceDTO;

import java.time.LocalDate;

public class TestUtils {

    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Pedro");
        customer.setDrivers(createDriver());
        return customer;
    }

    public static Cars createCar() {
        Cars car = new Cars();
        car.setId(1L);
        car.setModel("Gol");
        car.setFipeValue(10000F);
        car.setYear(String.valueOf(2021));
        car.setManufacturer("Volkswagen");
        return car;
    }

    public static Insurances createInsurance() {
        Insurances insurance = new Insurances();
        insurance.setId(1L);
        insurance.setCustomer(createCustomer());
        insurance.setCars(createCar());
        insurance.setIsActive(true);
        return insurance;
    }

    public static Drivers createDriver() {
        Drivers driver = new Drivers();
        driver.setId(1L);
        driver.setBirthdate(LocalDate.of(1990, 1, 1));
        return driver;
    }

    public static Claims createClaim() {
        Claims claim = new Claims();
        claim.setId(1L);
        claim.setDrivers(createDriver());
        claim.setCars(createCar());
        claim.setEventDate(LocalDate.of(2021, 1, 1));
        return claim;
    }

    public static UpdateInsuranceDTO createUpdateInsuranceDTO() {
        UpdateInsuranceDTO updateInsuranceDTO = new UpdateInsuranceDTO();
        updateInsuranceDTO.setCustomerId(1L);
        updateInsuranceDTO.setCarId(1L);
        updateInsuranceDTO.setIsActive(true);
        return updateInsuranceDTO;
    }

    public static CreateInsuranceDTO createCreateInsuranceDTO() {
        CreateInsuranceDTO createInsuranceDTO = new CreateInsuranceDTO();
        createInsuranceDTO.setCustomerId(1L);
        createInsuranceDTO.setCarId(1L);
        return createInsuranceDTO;
    }

    public static CreateInsuranceDTO createCreateInsuranceDTO(Long customerId, Long carId) {
        CreateInsuranceDTO createInsuranceDTO = new CreateInsuranceDTO();
        createInsuranceDTO.setCustomerId(customerId);
        createInsuranceDTO.setCarId(carId);
        return createInsuranceDTO;
    }
}
