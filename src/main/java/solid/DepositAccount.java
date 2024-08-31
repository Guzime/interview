package solid;

import java.math.BigDecimal;

public class DepositAccount extends Account {
    public DepositAccount(String numberAccount, BigDecimal balance) {
        super(numberAccount, balance);
    }

    @Override
    public BigDecimal getBalance() {
        System.out.println("You request balance on Deposit account " + numberAccount);
        return super.getBalance();
    }

    @Override
    public void refill(BigDecimal sum) {
        System.out.println("You refill balance on Deposit account " + numberAccount + " for sum " + sum);
        super.refill(sum);
    }
}
