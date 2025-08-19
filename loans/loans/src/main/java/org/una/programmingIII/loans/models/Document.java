package org.una.programmingIII.loans.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name="documents")
@Data
public class Document {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false) private String documentType;
  @Column(nullable=false) private String filePath;

  @Enumerated(EnumType.STRING) @Column(nullable=false)
  private DocumentState state = DocumentState.ACTIVE;

  @ManyToOne @JoinColumn(name="credit_application_id")
  private CreditApplication creditApplication;
}
