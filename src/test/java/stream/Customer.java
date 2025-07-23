package stream;

import solid.liskov.Account;

import java.util.List;

public class Customer {
    private final String name;
    private final int age;
    private final List<Account> accounts;

    public Customer(String name, int age, List<Account> accounts) {
        this.name = name;
        this.age = age;
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", account=" + accounts +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Account> getAccount() {
        return accounts;
    }
}
