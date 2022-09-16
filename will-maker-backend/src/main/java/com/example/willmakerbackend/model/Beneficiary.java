package com.example.willmakerbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Beneficiary {
   private String name;
   private String etherAddress;
   private float percentage;
}
