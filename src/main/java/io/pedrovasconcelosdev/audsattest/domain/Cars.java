package io.pedrovasconcelosdev.audsattest.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Cars {

    @Id
    @GeneratedValue(generator = "sq_cars")
    @SequenceGenerator(name = "sq_cars", sequenceName = "sq_cars", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "`year`")
    private String year;

    @Column(name = "fipe_value")
    private Float fipeValue;

}
