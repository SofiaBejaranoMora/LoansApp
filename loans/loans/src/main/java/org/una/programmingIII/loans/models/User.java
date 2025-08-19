package org.una.programmingIII.loans.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity @Table(name="users")
@Data
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false) private String name;
  @Column(nullable=false, unique=true) private String email;
  @Column(nullable=false) private String password;
  @Column(nullable=false, unique=true) private String identificationNumber;

  @Enumerated(EnumType.STRING) @Column(nullable=false)
  private UserState state = UserState.ACTIVE;

  @Column(nullable=false, updatable=false) private LocalDateTime createdAt;
  @Column(nullable=false) private LocalDateTime lastUpdate;

  @PrePersist void onCreate(){ createdAt = LocalDateTime.now(); lastUpdate = LocalDateTime.now(); }
  @PreUpdate  void onUpdate(){ lastUpdate = LocalDateTime.now(); }
}
