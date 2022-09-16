package com.example.citizenoraclegov.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Citizen {
    private String name;
    private String socialId;
    private boolean isAlive;
}
