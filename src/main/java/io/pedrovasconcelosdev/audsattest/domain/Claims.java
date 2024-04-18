package io.pedrovasconcelosdev.audsattest.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "claims")
public class Claims {

    @Id
    @GeneratedValue(generator = "sq_claims")
    @SequenceGenerator(name = "sq_claims", sequenceName = "sq_claims", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Drivers drivers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Cars cars;

}
