package solid;

import java.math.BigDecimal;

public class Account {
    protected final String numberAccount;
    protected BigDecimal balance;

    public Account(String numberAccount, BigDecimal balance) {
        this.balance = balance;
        this.numberAccount = numberAccount;
    }

    @Override
    public String toString() {
        return "number account = " + numberAccount
                + "\n balance = " + balance;
    }

    public BigDecimal getBalance() {
        System.out.println("You request balance on account " + numberAccount);
        return balance;
    }

    public void refill(BigDecimal sum) {
        System.out.println("You refill balance on account " + numberAccount + " for sum " + sum);
        balance = balance.add(sum);
    }

    public void payment(BigDecimal sum) {
        System.out.println("You payment on account " + numberAccount + " for sum " + sum);
        balance = balance.subtract(sum);
    }
}
