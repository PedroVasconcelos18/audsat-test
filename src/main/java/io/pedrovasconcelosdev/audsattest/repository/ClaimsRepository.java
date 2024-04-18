package io.pedrovasconcelosdev.audsattest.repository;

import io.pedrovasconcelosdev.audsattest.domain.CarDrivers;
import io.pedrovasconcelosdev.audsattest.domain.Cars;
import io.pedrovasconcelosdev.audsattest.domain.Claims;
import io.pedrovasconcelosdev.audsattest.domain.Drivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimsRepository extends JpaRepository<Claims, Long> {

    Optional<Claims> findByCars(Cars cars);
    Optional<Claims> findByDrivers(Drivers drivers);

}
