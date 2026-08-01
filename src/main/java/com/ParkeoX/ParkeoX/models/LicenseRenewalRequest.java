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
@Table(name = "license_renewal_request")
@Builder
public class LicenseRenewalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_license", nullable = false)
    private Licenses license;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_by_user_id", nullable = false)
    private Users requestedBy;

    @Column(nullable = false)
    private boolean resolved;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime requestedAt;
}
