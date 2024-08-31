package solid;

import java.math.BigDecimal;

public class SalaryAccount extends Account {


    public SalaryAccount(String numberAccount, BigDecimal balance) {
        super(numberAccount, balance);
    }
    @Override
    public BigDecimal getBalance() {
        System.out.println("You request balance on Salary account " + numberAccount);
        return balance;
    }
    @Override
    public void refill(BigDecimal sum) {
        System.out.println("You refill balance on Salary account " + numberAccount + " for sum " + sum);
        balance = balance.add(sum);
    }
    @Override
    public void payment(BigDecimal sum) {
        System.out.println("You payment on Salary account " + numberAccount + " for sum " + sum);
        balance = balance.subtract(sum);
    }
}
