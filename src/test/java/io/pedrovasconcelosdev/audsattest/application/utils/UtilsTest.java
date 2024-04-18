package io.pedrovasconcelosdev.audsattest.application.utils;

import io.pedrovasconcelosdev.audsattest.domain.Customer;
import io.pedrovasconcelosdev.audsattest.domain.Drivers;
import io.pedrovasconcelosdev.audsattest.utils.CustomerUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    public void testGetCustomerAge() {
        Drivers driver = new Drivers();
        driver.setBirthdate(LocalDate.of(1990, 1, 1));

        Customer customer = new Customer();
        customer.setDrivers(driver);

        int expected = LocalDate.now().getYear() - 1990;
        int actual = CustomerUtils.getCustomerAge(customer);

        assertEquals(expected, actual);
    }

    @Test
    public void testIsBetween() {
        assertTrue(CustomerUtils.isBetween(25, 20, 30));
        assertFalse(CustomerUtils.isBetween(35, 20, 30));
    }

}


