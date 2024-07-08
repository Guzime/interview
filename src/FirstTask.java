import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FirstTask {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        for (int i = 0; i < stack.size(); i++) {
            Integer element = stack.peek();
            System.out.println(element);
        }

        Iterator<Integer> iter = stack.iterator();

        while (iter.hasNext()){
            System.out.println(iter.next());
        }


        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        for (Integer element : queue) {
            System.out.println(element);
        }
    }
}
