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
@Table(name = "licenses")
@Builder

public class Licenses {
    @Id
    private String id_license;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_LicenseType", nullable = false)
    private LicenseType licenseType;
    private Double price;
    private LocalDateTime beginAt;
    private LocalDateTime endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


}
