import java.util.Comparator;



public class Solver {
    
    private Stack<Board> solution;
    private int moves = -1;
    
    public Solver(Board initial)  {
        if (initial == null) {
            throw new NullPointerException();
        }
        Board twin = initial.twin();
        
        MinPQ<SearchNode> pq = new MinPQ<>(new PriorityComparator());
        MinPQ<SearchNode> pqTwin = new MinPQ<>(new PriorityComparator());
        pq.insert(new SearchNode(initial));
        pqTwin.insert(new SearchNode(twin));
        SearchNode goalNode = null;
        SearchNode goalTwinNode = null;
        while (goalNode == null && goalTwinNode == null) {
            SearchNode node = pq.delMin();
            SearchNode twinNode = pqTwin.delMin();
            if (node.board.isGoal()) {
                goalNode = node;
                continue;
            }
            if (twinNode.board.isGoal()) {
                goalTwinNode = twinNode;
                continue;
            }
            int nmove = node.moves + 1;
            for (Board nb : node.board.neighbors()) {
                if (node.previous == null || !node.previous.board.equals(nb)) {
                    pq.insert(new SearchNode(nb,nb.manhattan() + nmove, nmove, node));
                }
            }
            nmove = twinNode.moves + 1;
            for (Board nb : twinNode.board.neighbors()) {
                if (twinNode.previous == null || !twinNode.previous.board.equals(nb)) {
                    pqTwin.insert(new SearchNode(nb,nb.manhattan() + nmove, nmove, twinNode));
                }
            }
        }
        
        if (goalNode != null) {
            moves = goalNode.moves;
            solution = new Stack<>();
            while (goalNode != null) {
                solution.push(goalNode.board);
                goalNode = goalNode.previous;
            }
        }
    }
    
    private class PriorityComparator implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return o1.priority - o2.priority;
        }
        
    }
    
    private class SearchNode {
        private int priority;
        private int moves;
        private Board board;
        private SearchNode previous;
        
        SearchNode(Board b) {
            this.board = b;
        }
        
        SearchNode(Board b, int priority, int moves, SearchNode previous) {
            this.board = b;
            this.priority = priority;
            this.moves = moves;
            this.previous = previous;
        }
    }
    
    public boolean isSolvable() {
        return solution != null;
    }
    
    public int moves() {
        return moves;
    }
    
    public Iterable<Board> solution() {
        return solution;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        int dimension = Integer.parseInt(in.readLine());
        int[][] blocks = new int[dimension][dimension];
        int i = 0;
        while (i < dimension && in.hasNextLine()) {
            String[] row = in.readLine().trim().split("\\s+");
            int j = 0;
            for (String val : row) {
                blocks[i][j] = Integer.parseInt(val);
                j++;
            }
            i++;
        }
        
        Solver solver = new Solver(new Board(blocks));
        if (solver.isSolvable()) {
            StdOut.printf("Minimum number of moves = %s\n\n", solver.moves);
            for (Board b : solver.solution) {
                StdOut.println(b);
                StdOut.println();
            }
        } else {
            StdOut.println("No solution possible");
        }

    }

}
