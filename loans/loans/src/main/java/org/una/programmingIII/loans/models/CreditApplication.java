package org.una.programmingIII.loans.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name="credit_applications")
@Data
public class CreditApplication {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false) private Double amount;
  @Column(nullable=false) private Integer term;
  @Column(nullable=false) private String purpose;

  @Enumerated(EnumType.STRING) @Column(nullable=false)
  private ApplicationStatus status = ApplicationStatus.REGISTERED;

  @ManyToOne @JoinColumn(name="applicant_id")
  private Client applicant;
}
