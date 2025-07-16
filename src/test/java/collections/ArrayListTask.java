package collections;

import org.junit.jupiter.api.Test;
import solid.liskov.Account;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListTask {

    @Test
    void showingCopy() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Long> numbers = new ArrayList<>();
        Object[] trueData = getCapacity(numbers);

        int sizeNumbers = trueData.length;
        System.out.println("Massive start - " + sizeNumbers);
        for (int i = 0; i < 100; i++) {
            numbers.add((long) i);
            trueData = getCapacity(numbers);
            if (sizeNumbers < trueData.length) {
                sizeNumbers = trueData.length;
                System.out.println("Massive size now - " + sizeNumbers + "\t|\t" + "at i - " + i + "\t|\t");
            }
        }
    }

    private Object[] getCapacity(ArrayList<Long> numbers) throws NoSuchFieldException, IllegalAccessException {
        Field elementData = numbers.getClass().getDeclaredField("elementData");
        elementData.setAccessible(true);
        Object[] trueData = (Object[])elementData.get(numbers);
        return trueData;
    }

}
