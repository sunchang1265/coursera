import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int size = 100;
        Percolation p = new Percolation(size);
        Random r = new Random();
        int row, col, times=0;
        double count = 0.0;
        while(!p.percolates()){
            row = r.nextInt(size) + 1;
            col = r.nextInt(size) + 1;
            p.open(row, col);
            count += (p.numberOfOpenSites());
            times++;
        }
        System.out.println("Count: " + count);
        System.out.println("*p: " + (count/times/size/size));
        System.out.println("Percolation: " + p.percolates());
    }
}
