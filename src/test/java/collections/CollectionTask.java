package collections;

import collection.BadHashCode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class CollectionTask {

    @Test
    void showingCopyInArrayList() throws NoSuchFieldException, IllegalAccessException {
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
        Object[] trueData = (Object[]) elementData.get(numbers);
        return trueData;
    }

    @Test
    void showingBucketsInHashMap() throws NoSuchFieldException, IllegalAccessException {
        HashMap<BadHashCode, String> hashMapWithBadKey = new HashMap<>();
        hashMapWithBadKey.put(new BadHashCode(4), "test");
        hashMapWithBadKey.put(new BadHashCode(5), "test");
        hashMapWithBadKey.put(new BadHashCode(6), "test");
        System.out.println(hashMapWithBadKey);

        Field tableField = hashMapWithBadKey.getClass().getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] trueTable = (Object[]) tableField.get(hashMapWithBadKey);

        for (int i = 0; i < trueTable.length; i++) {
            if (trueTable[i] != null) {
                Field next = trueTable[i].getClass().getDeclaredField("next");
                next.setAccessible(true);
                Object nextObjectInBucket = (Object) next.get(trueTable[i]);
                System.out.println("trueTable at i = " + i + ", value is " + trueTable[i] + ", next is " + nextObjectInBucket);
            } else {
                System.out.println("trueTable at i = " + i + ", value is " + trueTable[i]);
            }
        }
    }
}
