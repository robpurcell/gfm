package com.robbyp.batch.gfm.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@ToString
public class Fail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    @Getter @Setter private LocalDate tradeDate;
    @Getter @Setter private LocalDate intendedSettlementDate;
    @Getter @Setter private Integer quantity;
    @Getter @Setter private String intrumentId;
    @Getter @Setter private BigDecimal consideration;
    @Getter @Setter private String counterparty;
}
