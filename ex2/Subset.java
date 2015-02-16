
public class Subset {

    public static void main(String[] args) {
        int subset = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
            if (queue.size() == subset+1) {
                queue.dequeue();
            }
        }
        
        for (String in : queue) {
            StdOut.println(in);
        }
    }

}
