package com.example.citizenoraclegov.controller;

import com.example.citizenoraclegov.model.Citizen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/citizen")
public class CitizenStatusController {
    private static final Map<String, Citizen> citizenMap = Map.of(
            "ctz-20001", new Citizen("Alexei", "ctz-20001", true),
            "ctz-30001", new Citizen("Lisa", "ctz-30001", true),
            "ctz-40001", new Citizen("Walter", "ctz-40001", true)
    );
    @GetMapping("/{citizenId}")
    public Citizen getCitizen(@PathVariable String citizenId) {
        return citizenMap.get(citizenId);
    }

    @PutMapping("/report-dead/{citizenId}")
    public Citizen reportDead(@PathVariable String citizenId) {
       var citizen = citizenMap.get(citizenId) ;

       if (citizen == null) {
           throw new RuntimeException("Invalid citizen");
       }

       citizen.setAlive(false);

       return citizen;
    }
}
