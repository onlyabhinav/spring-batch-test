package com.onlyabhinav.spring.batch.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_ADV_CON_MD_TEST")
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Estimate {
    @Id
    @Column(name = "RID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rid;

    @Column(name = "ADVANCED_CON_ID")
    private long advancedConId;

    @Column(name = "FSYM_ID")
    private String fsymId;

    @Column(name = "FE_ITEM")
    private String feItem;
    @Column(name = "FE_FP_END")
    private Date feFpEndDate;
    @Column(name = "CONS_START_DATE")
    private Date consStartDate;
    @Column(name = "CONS_END_DATE")
    private Date consEndDate;
    @Column(name = "CURRENCY")

    private String currency;
    @Column(name = "FE_PER_REL")
    private Double fePerRel;
    @Column(name = "ADJDATE")
    private Date adjDate;
    @Column(name = "FE_MEAN")
    private  Double feMean;
    @Column(name = "FE_MEDIAN")
    private  Double feMedian;
    @Column(name = "FE_NUM_EST")

    private  Double feNumEst;
    @Column(name = "FE_LOW")
    private  Double feLow;
    @Column(name = "FE_HIGH")
    private  Double feHigh;
    @Column(name = "FE_STD_DEV")
    private  Double feStdDev;
    @Column(name = "FE_UP")
    private  Double feUp;
    @Column(name = "FE_DOWN")
    private  Double feDown;

    @Column(name = "DELETED")
    private  Double deleted;
    @Column(name = "MARKET_DATA_AUDIT_ID")
    private  Double marketDataAuditId;
}