import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
class cards {
    public static void main(String[] args) throws FileNotFoundException{
        long startTime = System.currentTimeMillis();
        Scanner in = new Scanner(new File("cards.in"));
        PrintWriter out = new PrintWriter(new File("cards.out"));
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            int[] cards = new int[num];
            //list of indexes for each card
            int[] index = new int[num + 1];

            for (int j = 0; j < num; j++) {
                int number = in.nextInt();
                cards[j] = number;
                index[number] = j;
            }
            //the smallest value of the cards
            int least = 1;
            //the index of the top of the cards
            int head = 0;
            long points = 0;
            //sum of all the cards SUM(1,2,3...n) = n*(n+1)/2
            long total = (long)num * ((long)num + 1) / 2;

            for (int j = 0; j < num; j++) {
                long subTotal = 0;
                int indexLeast = index[least];

                if (head < indexLeast) {
                    for (int k = head; k < indexLeast; k++) {
                        subTotal=subTotal + cards[k];
                    }
                } else if (head > indexLeast) {
                    for (int k = indexLeast; k < head; k++) {
                        subTotal=subTotal + cards[k];
                    }
                }

                if (subTotal != 0) { 
                    long otherTotal = total - subTotal;
                    if (subTotal > otherTotal) {
                        points=points + otherTotal;
                    } else {
                        points=points + subTotal;
                    }
                }

                total = total - least;
                cards[indexLeast] = 0;
                head = indexLeast + 1;
                if (head == num) {
                    head = 0;
                }
                least++;
            }
            out.println(points);
            //System.out.println(points);
        }
        out.close();
        in.close();
        System.out.println("Time taken: "+((System.currentTimeMillis() - startTime)/1000.0));
    }
}
