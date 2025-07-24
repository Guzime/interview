package stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import solid.liskov.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamOperationTest {
    @Test
    void showingPeekInStreamApi() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.stream()
                .peek(System.out::println)
                .toList();
    }

    @Test
    void showingMapInStreamApi() {
        List<Customer> customers = getCustomers();

        List<Integer> agesLessThen30 = customers.stream()
                .map(Customer::getAge)
                .filter(x -> x.compareTo(30) < 0)
                .toList();

        System.out.println(agesLessThen30);
    }

    @Test
    void showingFlatMapInStreamApi() {
        System.out.println(getCustomers().stream()
                .flatMap(x -> x.getAccount().stream())
                .filter(x -> x.getBalance().compareTo(new BigDecimal(200000)) > 0)
                .toList());
    }

    @Test
    @DisplayName("sorted() заставила выполниться сначала все map() и filter()")
    void showingStatelessAndStatefulOperation() {
        getCustomers().stream()
                .filter(x -> {
                    System.out.println("Filtering under 30 - " + x.getName());
                    return x.getAge() < 30;
                })
                .map(x -> {
                    System.out.println("Mapping into Stream<String> - " + x.getName());
                    return x.getName();
                })
                .sorted()
                .forEach(x -> System.out.println("Foreach - " + x));
    }

    private List<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        customers.add(new Customer("Nikita", 29, Arrays.asList(
                new Account("1237890SD23789", new BigDecimal(200000)),
                new Account("2348901ASD1234", new BigDecimal(50000))
        )));

        customers.add(new Customer("Anna", 34, Arrays.asList(
                new Account("9876543GH12345", new BigDecimal(150000))
        )));

        customers.add(new Customer("Ivan", 42, Arrays.asList(
                new Account("5637281KL93721", new BigDecimal(320000)),
                new Account("1122334AA99887", new BigDecimal(145000)),
                new Account("7766554BB12345", new BigDecimal(89000))
        )));

        customers.add(new Customer("Maria", 25, Arrays.asList(
                new Account("3456729MN43728", new BigDecimal(87000))
        )));

        customers.add(new Customer("Dmitry", 38, Arrays.asList(
                new Account("7829134OP19283", new BigDecimal(275000)),
                new Account("9988776CC11223", new BigDecimal(120000))
        )));

        customers.add(new Customer("Olga", 31, Arrays.asList(
                new Account("9283746QR83746", new BigDecimal(110000))
        )));

        customers.add(new Customer("Sergey", 45, Arrays.asList(
                new Account("2398471ST92834", new BigDecimal(400000)),
                new Account("12341234ABCD56", new BigDecimal(300000))
        )));

        customers.add(new Customer("Elena", 27, Arrays.asList(
                new Account("1239847UV92384", new BigDecimal(95000))
        )));

        customers.add(new Customer("Pavel", 36, Arrays.asList(
                new Account("7821349WX78123", new BigDecimal(198000)),
                new Account("5544332DE66543", new BigDecimal(102000))
        )));

        customers.add(new Customer("Tatiana", 30, Arrays.asList(
                new Account("6482731YZ64827", new BigDecimal(134000)),
                new Account("3210987XZ34567", new BigDecimal(88000))
        )));

        return customers;
    }
}
