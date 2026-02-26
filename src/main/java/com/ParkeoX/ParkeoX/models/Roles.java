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
@Table(name = "role")
@Builder
public class Roles {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String rol;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
