package io.pedrovasconcelosdev.audsattest.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "car_drivers")
public class CarDrivers {

    @Id
    @GeneratedValue(generator = "sq_car_drivers")
    @SequenceGenerator(name = "sq_car_drivers", sequenceName = "sq_car_drivers", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_main_driver")
    private Boolean isMainDriver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Drivers drivers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Cars cars;

}
