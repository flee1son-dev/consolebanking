package com.flee1son_dev.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration  
@PropertySource("classpath:application.properties")
public class AccountProperties {
    private final double defaultAmount;
    private final double transferComission;

    public AccountProperties(
        @Value("${account.default-amount}")double defaultAmount,
        @Value("${account.transfer-comission}") double transferComission
    ){
        this.defaultAmount = defaultAmount;
        this.transferComission = transferComission;
    }

    public double getDefaultAmount() {
        return defaultAmount;
    }

    public double getTransferComission() {
        return transferComission;
    }
}
