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
        return balance;
    }

    public void refill(BigDecimal sum) {
        balance = balance.add(sum);
    }
}
