import java.util.ArrayList;
import java.util.List;


public class Board {
    
    private int[][] blocks;
    private Pos blankPos;
    
    public Board(int[][] blocks) {
        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
                if (blocks[i][j] == 0) {
                    blankPos = new Pos(i, j);
                }
            }
        }
    }
    
    public int dimension() {
        return this.blocks.length;
    }
    
    public int hamming() {
        int expectedVal = 1;
        int distance = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if ( !isLastBlock(i, j) && blocks[i][j] != expectedVal) {
                    distance++;
                }
                expectedVal++;
            }
        }
        return distance;
    }
    
    public int manhattan() {
        int expectedVal = 1;
        int distance = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if ( blocks[i][j] != 0 && blocks[i][j] != expectedVal) {
                    Pos correctPos = getPosForVal(blocks[i][j]);
                    distance += Math.abs(i - correctPos.i) + Math.abs(j - correctPos.j);
                }
                expectedVal++;
            }
        }
        return distance;
    }
    private Pos getPosForVal(int val) {
        val -= 1;
        int i = val / dimension();
        int j = val % dimension();
        return new Pos(i, j);
    }
    private class Pos {
        private int i;
        private int j;
        
        Pos(int i, int j) {
           this.i = i;
           this.j = j;
        }
    }
    public boolean isGoal() {
        int expectedVal = 1;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if ( !isLastBlock(i, j) && blocks[i][j] != expectedVal) {
                    return false;
                }
                expectedVal++;
            }
        }
        return true;
    }
    
    private boolean isLastBlock(int i, int j) {
        return i == j && i == blocks.length - 1;
    }
    public Board twin() {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length - 1; j++) {
                if (blocks[i][j] != 0 && blocks[i][j + 1] != 0) {
                    int tmp = blocks[i][j];
                    blocks[i][j] = blocks[i][j + 1];
                    blocks[i][j + 1] = tmp;
                    Board b = new Board(blocks);
                    blocks[i][j + 1] = blocks[i][j];
                    blocks[i][j] = tmp;
                    return b;
                }
            }
        }
        return null;
    }
    
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }
        Board b = (Board) y;
        if (this.dimension() != b.dimension()) {
            return false;
        }
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (this.blocks[i][j] != b.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>(4);
        if (blankPos.i + 1 < dimension()) {
            blocks[blankPos.i][blankPos.j] = blocks[blankPos.i + 1][blankPos.j];
            blocks[blankPos.i + 1][blankPos.j] = 0;
            neighbors.add(new Board(blocks));
            blocks[blankPos.i + 1][blankPos.j] = blocks[blankPos.i][blankPos.j];
            blocks[blankPos.i][blankPos.j] = 0;
        }
        if (blankPos.i - 1 >= 0) {
            blocks[blankPos.i][blankPos.j] = blocks[blankPos.i - 1][blankPos.j];
            blocks[blankPos.i - 1][blankPos.j] = 0;
            neighbors.add(new Board(blocks));
            blocks[blankPos.i - 1][blankPos.j] = blocks[blankPos.i][blankPos.j];
            blocks[blankPos.i][blankPos.j] = 0;
        }
        if (blankPos.j + 1 < dimension()) {
            blocks[blankPos.i][blankPos.j] = blocks[blankPos.i][blankPos.j + 1];
            blocks[blankPos.i][blankPos.j + 1] = 0;
            neighbors.add(new Board(blocks));
            blocks[blankPos.i][blankPos.j + 1] = blocks[blankPos.i][blankPos.j];
            blocks[blankPos.i][blankPos.j] = 0;
        }
        if (blankPos.j - 1 >= 0) {
            blocks[blankPos.i][blankPos.j] = blocks[blankPos.i][blankPos.j - 1];
            blocks[blankPos.i][blankPos.j - 1] = 0;
            neighbors.add(new Board(blocks));
            blocks[blankPos.i][blankPos.j - 1] = blocks[blankPos.i][blankPos.j];
            blocks[blankPos.i][blankPos.j] = 0;
        }
        return neighbors;
    }
    public String toString() {
        String str = Integer.toString(dimension());
        for (int i = 0; i < blocks.length; i++) {
            str += "\n";
            for (int j = 0; j < blocks.length; j++) {
                str += blocks[i][j] + " ";
            }
        }
        return str;
    }
    public static void main(String[] args) {
        Board b = new Board(new int[][]{{4,1,2},{3,0,5},{6,7,8}});
        StdOut.println(b);
        for (Board n : b.neighbors()) {
            StdOut.println(n);
        }
        //StdOut.println(b.twin());
        //StdOut.println(b.isGoal());
        //StdOut.println(b.hamming());
        //StdOut.println(b.manhattan());
    }
}
