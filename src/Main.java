import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int size = 3;
        Percolation p = new Percolation(size);
        Random r = new Random();
        int row, col;
        /*while(!p.percolates()){
            row = r.nextInt(size) + 1;
            col = r.nextInt(size) + 1;
            p.open(row, col);
            //p.renderBoard();
        }*/
        p.open(1, 3);
        p.renderBoard();
        p.open(2, 3);
        p.renderBoard();
        p.open(3, 3);
        p.renderBoard();
        p.open(3, 1);
        p.renderBoard();
        p.open(2, 1);
        p.renderBoard();
        p.open(1, 1);
        p.renderBoard();
        System.out.println("Percolation: " + p.percolates());
        p.renderBoard();
    }
}
