package com.example.trafficpoliceman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarNumberEntity {

    @Id
    Long carNumber;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createDate;
}
