package org.una.programmingIII.loans.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name="roles")
@Data
public class Role {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, unique=true)
  private String name;

  @Enumerated(EnumType.STRING) @Column(nullable=false)
  private RoleState state = RoleState.ACTIVE;
}
