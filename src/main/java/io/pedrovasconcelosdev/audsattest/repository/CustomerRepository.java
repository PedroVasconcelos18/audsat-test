package io.pedrovasconcelosdev.audsattest.repository;

import io.pedrovasconcelosdev.audsattest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
