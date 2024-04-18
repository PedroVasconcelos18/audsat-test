package io.pedrovasconcelosdev.audsattest.repository;

import io.pedrovasconcelosdev.audsattest.domain.CarDrivers;
import io.pedrovasconcelosdev.audsattest.domain.Insurances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDriversRepository extends JpaRepository<CarDrivers, Long> {

}
