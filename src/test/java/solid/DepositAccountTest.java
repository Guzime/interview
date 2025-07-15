package solid;

import org.junit.jupiter.api.Test;
import solid.liskov.DepositAccount;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DepositAccountTest {

    @Test
    void getBalance() {
        DepositAccount account = new DepositAccount("S90983RUB234093", new BigDecimal(64000));
        BigDecimal balance = account.getBalance();
        assertEquals(balance, new BigDecimal(64000));
    }


    @Test
    void refill() {
        DepositAccount account = new DepositAccount("S90983RUB234093", new BigDecimal(64000));
        account.refill(new BigDecimal(64000));
        assertEquals(account.getBalance(), new BigDecimal(128000));
    }
}