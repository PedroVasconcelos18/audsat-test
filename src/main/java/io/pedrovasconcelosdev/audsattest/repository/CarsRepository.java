package io.pedrovasconcelosdev.audsattest.repository;

import io.pedrovasconcelosdev.audsattest.domain.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {

}
