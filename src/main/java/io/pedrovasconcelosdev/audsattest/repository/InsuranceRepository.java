package io.pedrovasconcelosdev.audsattest.repository;

import io.pedrovasconcelosdev.audsattest.domain.Insurances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurances, Long> {

}
