package io.pedrovasconcelosdev.audsattest.utils;

import io.pedrovasconcelosdev.audsattest.domain.Customer;

import java.time.LocalDate;
import java.time.Period;

public class CustomerUtils {

    public static int getCustomerAge(Customer customer) {
        LocalDate birthdate = customer.getDrivers().getBirthdate();
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    public static boolean isBetween(int age, int lowerBound, int upperBound) {
        return age >= lowerBound && age <= upperBound;
    }

}
