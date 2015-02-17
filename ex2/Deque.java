import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    
    private ItemNode first;
    private ItemNode last;
    private int size;
    
    private class ItemNode {
        Item val;
        ItemNode next;
        ItemNode previous;
    }

    public Deque() {
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        ItemNode node = new ItemNode();
        node.val = item;
        node.next = first;
        
        if (size == 0) {
            last = node;
        } else {
           first.previous = node; 
        }
        first = node;
        size++;
    }
    
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        ItemNode node = new ItemNode();
        node.val = item;
        if (size == 0) {
            first = node;
        } else {
            last.next = node;
            node.previous = last;
        }
        last = node;
        size++;
    }
    
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = first.val;
        first = first.next;
        size--;
        if (size == 0) {
            last = null;
        } else {
            first.previous = null;
        }
        return item;
    }
    
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = last.val;
        last = last.previous;
        size--;
        if (size == 0) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private ItemNode current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item item = current.val;
                current = current.next;
                return item;
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }
    
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        d.addLast(100);
        d.addFirst(2);
        d.addLast(200);
        for (int i : d) {
            StdOut.println(i);
        }
    }



}
