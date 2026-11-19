package flee1son.dev.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {
    @Value("${account.default-amount}") private int defaultMoneyAmount;
    @Value("${account.transfer-commission}") private double defaultTransferComission;



    public int getDefaultMoneyAmount() {
        return defaultMoneyAmount;
    }

    public double getDefaultTransferComission() {
        return defaultTransferComission;
    }
}
