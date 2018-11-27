package com.robbyp.batch.gfm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@ToString
public class EnrichedFail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    @Getter @Setter private LocalDate tradeDate;
    @Getter @Setter private LocalDate intendedSettlementDate;
    @Getter @Setter private Integer quantity;
    @Getter @Setter private String intrumentId;
    @Getter @Setter private BigDecimal consideration;
    @Getter @Setter private String counterparty;
    @Getter @Setter private BigDecimal failCost;
//    @Getter @Setter @OneToOne private Fail fail;
    @Getter @Setter private String status;

}
