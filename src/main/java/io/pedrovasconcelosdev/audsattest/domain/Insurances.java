package io.pedrovasconcelosdev.audsattest.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "insurances")
public class Insurances {

    @Id
    @GeneratedValue(generator = "sq_insurances")
    @SequenceGenerator(name = "sq_insurances", sequenceName = "sq_insurances", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_dt")
    private LocalDate creationDt;

    @Column(name = "updated_dt")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Cars cars;

}
