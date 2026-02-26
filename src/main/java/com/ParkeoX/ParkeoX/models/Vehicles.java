package com.ParkeoX.ParkeoX.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
@Builder

public class Vehicles {
    @Id
    private String plateNo;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_brand", nullable = true)
    private Brand brand;

    @Column(nullable = true)
    private String model;

    @Column(nullable = true)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicleType_id")
    private VehicleType vehicleType;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
