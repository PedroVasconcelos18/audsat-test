package io.pedrovasconcelosdev.audsattest.application.service;

import io.pedrovasconcelosdev.audsattest.domain.*;
import io.pedrovasconcelosdev.audsattest.dto.CreateInsuranceDTO;
import io.pedrovasconcelosdev.audsattest.dto.InsuranceDTO;
import io.pedrovasconcelosdev.audsattest.dto.InsuranceUpdatedDTO;
import io.pedrovasconcelosdev.audsattest.dto.UpdateInsuranceDTO;
import io.pedrovasconcelosdev.audsattest.repository.CarsRepository;
import io.pedrovasconcelosdev.audsattest.repository.ClaimsRepository;
import io.pedrovasconcelosdev.audsattest.repository.CustomerRepository;
import io.pedrovasconcelosdev.audsattest.repository.InsuranceRepository;
import io.pedrovasconcelosdev.audsattest.service.impl.InsuranceServiceImpl;
import io.pedrovasconcelosdev.audsattest.TestUtils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InsuranceServiceTest {

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    @Mock
    private InsuranceRepository insuranceRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CarsRepository carsRepository;

    @Mock
    private ClaimsRepository claimsRepository;

    private CreateInsuranceDTO createInsuranceDTO;
    private UpdateInsuranceDTO updateInsuranceDTO;
    private Customer customer;
    private Cars car;
    private Insurances insurance;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        createInsuranceDTO = TestUtils.createCreateInsuranceDTO();
        updateInsuranceDTO = TestUtils.createUpdateInsuranceDTO();
        customer = TestUtils.createCustomer();
        car = TestUtils.createCar();
        insurance = TestUtils.createInsurance();
    }

    @Test
    public void shouldReturnIdOfNewInstance() {
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));
        when(carsRepository.findById(any(Long.class))).thenReturn(Optional.of(car));
        when(insuranceRepository.save(any(Insurances.class))).thenReturn(insurance);

        Insurances result = insuranceService.createInsurance(createInsuranceDTO);

        assertEquals(insurance.getId(), result.getId());
    }

    @Test
    public void shouldReturnEntityNotFoundWhenSearchForAnNonexistentCustomerOnCreate() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insuranceService.createInsurance(createInsuranceDTO));
    }

    @Test
    public void shouldReturnEntityNotFoundWhenSearchForAnNonexistentCarOnCreate() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer()));
        when(carsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insuranceService.createInsurance(createInsuranceDTO));
    }


    @Test
    public void shouldReturnInsuranceWithTheBudgetAndFipePercentageValues() {
        Long id = 1L;
        when(insuranceRepository.findById(any(Long.class))).thenReturn(Optional.of(insurance));

        InsuranceDTO result = insuranceService.getInsuranceById(id);

        assertEquals(insurance.getId(), result.getCarId());
        assertEquals(insurance.getCustomer().getId(), result.getCustomerId());
        assertEquals(insurance.getCars().getId(), result.getCarId());
        assertEquals(insurance.getIsActive(), result.getIsActive());
        assertEquals("R$ 10000.00", result.getCarValue());
        assertEquals("6%", result.getFipePercentage());
        assertEquals("R$ 600.00", result.getBudget());
    }

    @Test
    public void shouldReturnEntityNotFoundWhenSearchForAnNonexistentInsuranceOnGet() {
        Long id = 1L;

        when(insuranceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insuranceService.getInsuranceById(id));
    }

    @Test
    public void shouldReturnTheUpdatedInsurance() {
        Long id = 1L;

        when(insuranceRepository.findById(any(Long.class))).thenReturn(Optional.of(insurance));
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(insurance.getCustomer()));
        when(carsRepository.findById(any(Long.class))).thenReturn(Optional.of(insurance.getCars()));

        InsuranceUpdatedDTO result = insuranceService.editInsurance(id, updateInsuranceDTO);

        assertEquals(insurance.getCustomer().getId(), result.getCustomerId());
        assertEquals(insurance.getCars().getId(), result.getCarId());
        assertEquals(insurance.getIsActive(), result.getIsActive());
    }

    @Test
    public void shouldReturnEntityNotFoundWhenSearchForAnNonexistentInsuranceOnEdit() {
        Long id = 1L;

        when(insuranceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insuranceService.editInsurance(id, updateInsuranceDTO));
    }

    @Test
    public void testDeleteInsurance() {
        Long id = 1L;

        when(insuranceRepository.findById(any(Long.class))).thenReturn(Optional.of(insurance));

        insuranceService.deleteInsurance(id);

        verify(insuranceRepository, times(1)).delete(insurance);
    }

    @Test
    public void testDeleteInsurance_NotFound() {
        Long id = 1L;

        when(insuranceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insuranceService.deleteInsurance(id));
    }
}
