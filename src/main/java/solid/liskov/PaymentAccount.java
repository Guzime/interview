package solid.liskov;

import java.math.BigDecimal;

public class PaymentAccount extends Account{
    public PaymentAccount(String numberAccount, BigDecimal balance) {
        super(numberAccount, balance);
    }

    public void payment(BigDecimal sum) {
        System.out.println("You payment on account " + numberAccount + " for sum " + sum);
        balance = balance.subtract(sum);
    }
}
