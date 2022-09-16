package com.example.willmakerbackend.store;

import com.example.willmakerbackend.model.WillDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Component
@Getter
@Setter
public class ContractBean {
    private boolean contractCreated = false;
    private String contractBinaryCode = null;
    private WillDetails currentWillDetails  = new WillDetails() ;
}
