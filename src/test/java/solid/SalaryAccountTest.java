package solid;

import org.junit.jupiter.api.Test;
import solid.liskov.SalaryAccount;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SalaryAccountTest {

    @Test
    void getBalance() {
        SalaryAccount account = new SalaryAccount("S90983RUB234093", new BigDecimal(64000));
        BigDecimal balance = account.getBalance();
        assertEquals(balance, new BigDecimal(64000));
    }

    @Test
    void refill() {
        SalaryAccount account = new SalaryAccount("S90983RUB234093", new BigDecimal(64000));
        account.refill(new BigDecimal(64000));
        assertEquals(account.getBalance(), new BigDecimal(128000));
    }

    @Test
    void payment() {
        SalaryAccount account = new SalaryAccount("S90983RUB234093", new BigDecimal(64000));
        account.payment(new BigDecimal(63000));
        assertEquals(account.getBalance(), new BigDecimal(1000));
    }
}