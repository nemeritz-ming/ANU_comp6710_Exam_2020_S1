package comp1110.exam;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class Q4ArrayQueueTest {

    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    Integer diff = new Random().nextInt(25) + 1;

    static class Thing {
        final String name;

        public Thing(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Thing " + name;
        }
    }

    @Test
    public void testEnqueueNotEmpty() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        assertTrue("Newly created queue should be empty!", queue.isEmpty());
        Thing thing1 = new Thing("One");
        queue.enqueue(thing1);
        assertFalse("Queue should not be empty after one enqueue", queue.isEmpty());
        Thing thing2 = new Thing("Two");
        queue.enqueue(thing2);
        assertFalse("Queue should not be empty after two enqueues", queue.isEmpty());
    }

    @Test
    public void testEnqueueTwoString() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        checkToString(queue, "");
        Thing thing1 = new Thing("One");
        queue.enqueue(thing1);
        checkToString(queue, "Thing One");
        Thing thing2 = new Thing("Two");
        queue.enqueue(thing2);
        checkToString(queue, "Thing One,Thing Two");
    }

    private <T> void checkToString(Q4ArrayQueue<T> queue, String expected) {
        assertTrue("Incorrect output from toString().  Expected \"" + expected + "\" but got \"" + queue.toString() + "\"", expected.equals(queue.toString()));
    }

    @Test
    public void testDequeueOne() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thingToDo = new Thing("to do");
        queue.enqueue(thingToDo);
        checkDequeue(queue, thingToDo);
        assertTrue("Queue should be empty after one enqueue and one dequeue", queue.isEmpty());
    }

    @Test
    public void testDequeueOneString() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thingToDo = new Thing("to do");
        queue.enqueue(thingToDo);
        Thing thingToSee = new Thing("to see");
        queue.enqueue(thingToSee);
        queue.dequeue();
        checkToString(queue, "Thing to see");
    }

    @Test
    public void testDequeueTwo() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thing1 = new Thing("One");
        queue.enqueue(thing1);
        Thing thing2 = new Thing("Two");
        queue.enqueue(thing2);
        checkDequeue(queue, thing1);
        assertFalse("Queue should not be empty after two enqueues and one dequeue", queue.isEmpty());
        checkDequeue(queue, thing2);
        assertTrue("Queue should be empty after two enqueues and two dequeues", queue.isEmpty());
    }

    @Test
    public void testDequeueTwoString() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thing1 = new Thing("One");
        queue.enqueue(thing1);
        Thing thing2 = new Thing("Two");
        queue.enqueue(thing2);
        queue.dequeue();
        checkToString(queue, "Thing Two");
        queue.dequeue();
        checkToString(queue, "");
    }

    @Test(expected = Q4ArrayQueue.EmptyQueueException.class)
    public void testDequeueEmpty() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        queue.dequeue();
    }

    @Test
    public void testEmptyString() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        checkToString(queue, "");
    }

    @Test
    public void testFirstOne() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thingToEat = new Thing("to eat");
        queue.enqueue(thingToEat);
        Thing v = queue.first();
        assertTrue("queue.first() returned " + v + ", expected " + thingToEat, thingToEat == v);
        assertFalse("Queue should not be empty after one enqueue and one first", queue.isEmpty());
    }

    @Test
    public void testFirstTwo() {
        Q4ArrayQueue<String> queue = new Q4ArrayQueue<>();
        String a = "a";
        queue.enqueue(a);
        String b = "b";
        queue.enqueue(b);
        String v1 = queue.first();
        assertTrue("queue.first() returned " + v1 + ", expected " + a, a.equals(v1));
        assertFalse("Queue should be empty after two enqueues and one first", queue.isEmpty());
        String v2 = queue.first();
        assertTrue("queue.first() returned " + v2 + ", expected " + a, a.equals(v2));
        assertFalse("Queue should be empty after two enqueues and two firsts", queue.isEmpty());
        queue.dequeue(); // throw away top value
        String v3 = queue.first();
        assertTrue("queue.first() returned " + v3 + ", expected " + b, b.equals(v3));
    }

    @Test
    public void testMultipleEnqueue() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thing1 = new Thing("One");
        queue.enqueue(thing1);
        Thing thing2 = new Thing("Two");
        queue.enqueue(thing2);
        queue.enqueue(thing2);
        checkDequeue(queue, thing1);
        checkDequeue(queue, thing2);
        checkDequeue(queue, thing2);
    }

    @Test
    public void testMultipleEnqueueString() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thing1 = new Thing("One");
        queue.enqueue(thing1);
        Thing thing2 = new Thing("Two");
        queue.enqueue(thing2);
        checkToString(queue, "Thing One,Thing Two");
        queue.enqueue(thing2);
        checkToString(queue, "Thing One,Thing Two,Thing Two");
        queue.dequeue();
        checkToString(queue, "Thing Two,Thing Two");
        queue.dequeue();
        checkToString(queue, "Thing Two");
    }

    @Test(expected = Q4ArrayQueue.EmptyQueueException.class)
    public void testFirstEmpty() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        queue.first();
    }

    @Test
    public void testNoMemoryLeaks() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thing1 = new Thing("One");
        queue.enqueue(thing1);
        WeakReference<Thing> reference1 = new WeakReference<>(thing1);
        Thing thing2 = new Thing("Two");
        queue.enqueue(thing2);
        WeakReference<Thing> reference2 = new WeakReference<>(thing2);
        checkDequeue(queue, thing1);
        checkDequeue(queue, thing2);
        thing1 = null;
        thing2 = null;

        Runtime.getRuntime().gc();
        assertNull("A reference still exists to thing1 somewhere in the queue!", reference1.get());
        assertNull("A reference still exists to thing2 somewhere in the queue!", reference2.get());
    }

    @Test
    public void testEnqueueDequeueLarge() {
        Q4ArrayQueue<String> queue = new Q4ArrayQueue<>();
        final int LENGTH = 500;
        List<String> range = IntStream.range(0, LENGTH).boxed().map(i -> String.valueOf(i)).collect(Collectors.toList());
        for (String item : range) {
            queue.enqueue(item);
        }
        for (int i=0; i<LENGTH; i++) {
            assertFalse("Queue should not be empty after dequeuing " + i + " elements", queue.isEmpty());
            String result = queue.dequeue();
            String expected = String.valueOf(i);
            assertTrue("queue.dequeue() returned " + result + ", expected " + expected, expected.equals(result));
        }
    }

    @Test
    public void testContainsTop() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thing1 = new Thing("One");
        Thing thing2 = new Thing("Two");
        assertFalse("queue.contains(thing1) returned true, expected false", queue.contains(thing1));
        assertFalse("queue.contains(thing2) returned true, expected false", queue.contains(thing2));
        queue.enqueue(thing1);
        assertTrue("queue.contains(thing1) returned false, expected true", queue.contains(thing1));
        queue.enqueue(thing2);
        assertTrue("queue.contains(thing2) returned false, expected true", queue.contains(thing2));
        queue.dequeue();
        assertTrue("queue.contains(thing2) returned false, expected true", queue.contains(thing2));
        queue.dequeue();
        assertFalse("queue.contains(thing1) returned true, expected false", queue.contains(thing1));
        assertFalse("queue.contains(null) returned true, expected false", queue.contains(null));
    }

    @Test
    public void testContainsBuried() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thing1 = new Thing("One");
        Thing thing2 = new Thing("Two");
        assertFalse("queue.contains(thing1) returned true, expected false", queue.contains(thing1));
        assertFalse("queue.contains(thing2) returned true, expected false", queue.contains(thing2));
        queue.enqueue(thing1);
        assertTrue("queue.contains(thing1) returned false, expected true", queue.contains(thing1));
        assertFalse("queue.contains(thing2) returned true, expected false", queue.contains(thing2));
        queue.enqueue(thing2);
        assertTrue("queue.contains(thing1) returned false, expected true", queue.contains(thing1));
        assertTrue("queue.contains(thing2) returned false, expected true", queue.contains(thing2));
        queue.dequeue();
        assertTrue("queue.contains(thing2) returned false, expected true", queue.contains(thing2));
        assertFalse("queue.contains(thing1) returned true, expected false", queue.contains(thing1));
        queue.dequeue();
        assertFalse("queue.contains(thing1) returned true, expected false", queue.contains(thing1));
        assertFalse("queue.contains(thing2) returned true, expected false", queue.contains(thing2));
    }

    @Test
    public void testContainsDuplicateRemoved() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thing1 = new Thing("One");
        Thing thing2 = new Thing("Two");
        queue.enqueue(thing1);
        queue.enqueue(thing2);
        queue.enqueue(thing1);
        queue.dequeue();
        assertTrue("queue.contains(thing1) returned false, expected true", queue.contains(thing1));
    }

    @Test
    public void testDequeueEmptyString() {
        Q4ArrayQueue<Thing> queue = new Q4ArrayQueue<>();
        Thing thingvellir = new Thing("vellir");
        queue.enqueue(thingvellir);
        queue.dequeue();
        try {
            queue.dequeue();
        } catch (Q4ArrayQueue.EmptyQueueException e) {
            // ignore the exception
        }
        checkToString(queue, "");
        queue.enqueue(thingvellir);
        checkToString(queue, "Thing vellir");
    }

    @Test
    public void testContainsDeep() {
        final int SIZE = 874;
        Q4ArrayQueue<String> queue = new Q4ArrayQueue<>();
        for (int i = 0; i < SIZE; i++) {
            queue.enqueue(String.valueOf(i));
        }
        assertFalse("queue.contains(null) returned true, expected false", queue.contains(null));
        for (int i = 0; i < SIZE; i++) {
            assertTrue("queue.contains(\"" + i + "\") returned false, expected true", queue.contains(String.valueOf(i)));
        }
    }

    private void checkDequeue(Q4ArrayQueue<Thing> queue, Thing expected) {
        Thing result = queue.dequeue();
        assertTrue("queue.dequeue() returned " + result + ", expected " + expected, expected == result);
    }

}
