import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Object[] items;
    private int size;
    private int next;

    public RandomizedQueue() {
        this(1);
    }
    
    private RandomizedQueue(int initialSize) {
        items = new Object[initialSize];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (next == items.length) {
            Object[] newItems = new Object[2*items.length];
            next = 0;
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    newItems[next++] = items[i];
                }
            }
            items = newItems;
        }
        items[next++] = item;
        size++;
    }
    
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = sampleIndex();
        Item item = (Item) items[idx];
        items[idx] = null;
        size--;
        if (size <= items.length/4) {
            Object[] newItems = new Object[items.length/2];
            int oldNext = next;
            next = 0;
            for (int i = 0; i < oldNext; i++) {
                if (items[i] != null) {
                    newItems[next++] = items[i];
                }
            }
            items = newItems;
        }
        return item;
        
    }
    
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (Item) items[sampleIndex()];
    }
    
    private int sampleIndex() {
        int i = 0;
        do {
            i = StdRandom.uniform(next);    
        } while(items[i] == null);
        return i;
    }
    
    private Object[] initIterator() {
        Object[] itemsIter = new Object[size];
        RandomizedQueue<Object> queue = new RandomizedQueue<>(size);
        for (int i = 0; i < next; i++) {
            if (items[i] != null) {
                queue.enqueue(items[i]);
            }
        }
        
        for (int i = 0; !queue.isEmpty(); i++) {
            itemsIter[i] = queue.dequeue();
        }
        return itemsIter;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int nextIdx; private Object[] itemsIter;
            { itemsIter = initIterator(); }
            @Override
            public boolean hasNext() {
                return nextIdx < itemsIter.length;
            }
            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (Item) itemsIter[nextIdx++];
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        for (int i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("---------");
        for (int i : q) {
            System.out.print(i + " ");
        }

    }
}
