package solid;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AccountTest {

    @Test
    void testToString() {
        Account account = new Account("S90983RUB234093", new BigDecimal(64000));
        String accountInfo = account.toString();
        System.out.println(accountInfo);
    }

    @Test
    void getBalance() {
        Account account = new Account("S90983RUB234093", new BigDecimal(64000));
        BigDecimal balance = account.getBalance();
        assertEquals(balance, new BigDecimal(64000));
    }

    @Test
    void refill() {
        Account account = new Account("S90983RUB234093", new BigDecimal(64000));
        account.refill(new BigDecimal(64000));
        assertEquals(account.getBalance(), new BigDecimal(128000));
    }

    @Test
    void payment() {
        Account account = new Account("S90983RUB234093", new BigDecimal(64000));
        account.payment(new BigDecimal(63000));
        assertEquals(account.getBalance(), new BigDecimal(1000));
    }
}