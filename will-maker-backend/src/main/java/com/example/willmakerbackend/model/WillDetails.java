package com.example.willmakerbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class WillDetails {
    private String creatorEtherAddress;
    private String creatorSocialId;
    private BigDecimal etherAmount;
    private List<Beneficiary> beneficiaryList;
}
