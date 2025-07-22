package stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StreamOperationTest {
    @Test
    void showingPeekInStreamApi() {
        ArrayList<Object> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.stream()
                .peek(x -> System.out.println(x))
                .collect(Collectors.toList());
    }
}
