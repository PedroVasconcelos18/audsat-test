package io.pedrovasconcelosdev.audsattest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "insurances")
public class Insurances {

    @Id
    @GeneratedValue(generator = "sq_insurances")
    @SequenceGenerator(name = "sq_insurances", sequenceName = "sq_insurances", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "creation_dt")
    private LocalDate creationDt;

    @UpdateTimestamp
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

    public Insurances(Boolean isActive, Customer customer, Cars cars) {
        this.isActive = isActive;
        this.customer = customer;
        this.cars = cars;
    }
}
