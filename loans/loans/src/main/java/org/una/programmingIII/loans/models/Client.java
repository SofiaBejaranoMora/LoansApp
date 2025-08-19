package org.una.programmingIII.loans.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false, unique=true)
    private String identificationNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ClientState state = ClientState.ACTIVE;

    // >>> campos que faltaban <<<
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime lastUpdate;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.lastUpdate = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}
