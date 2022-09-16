package com.example.willmakerbackend.controller;

import com.example.willmakerbackend.model.WillDetails;
import com.example.willmakerbackend.model.WillCreationResponse;
import com.example.willmakerbackend.store.ContractBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/will")
@CrossOrigin(origins = "*")
public class WillMakerController {

    private final ContractBean contractBean;
    public WillMakerController(ContractBean contractBean) {
        this.contractBean = contractBean;
    }
    @PostMapping
    public WillCreationResponse create(@RequestBody  WillDetails request) {
        contractBean.setCurrentWillDetails(request);
        return new WillCreationResponse();
    }

    @GetMapping
    public WillDetails getCurrentWill() {
       return this.contractBean.getCurrentWillDetails();
    }
}
