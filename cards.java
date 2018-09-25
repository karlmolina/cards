import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
class cards {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new File("cards.in"));
        PrintWriter out = new PrintWriter(new File("cards.out"));
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            int[] cards = new int[num];
            int[] index = new int[num + 1];
            for (int j = 0; j < num; j++) {
                int number = in.nextInt();
                cards[j] = number;
                index[number] = j;
            }
            for (int j = 0; j < num; j++) {
                //System.out.print(cards[j] + ":" + index[cards[j]] + ", ");
            }
            //System.out.println();	
            int least = 1;
            int head = 0;
            long points = 0;

            for (int j = 0; j < num; j++) {
                long leftTotal = 0, rightTotal = 0;
                int indexLeast = index[least];

                if (head < indexLeast) {
                    for (int k = 0; k < num; k++) {
                        if (k < head) {
                            rightTotal+=cards[k];

                        } else if (k >= head && k < indexLeast) {
                            leftTotal+=cards[k];
                        } else {
                            rightTotal+=cards[k];
                        }
                    }
                } else if (head > indexLeast) {
                    for (int k = 0; k < num; k++) {
                        if ( k < indexLeast) {
                            leftTotal+=cards[k];
                        } else if (k >= indexLeast && k < head) {
                            rightTotal+=cards[k];
                        } else {
                            leftTotal+=cards[k];
                        }
                    }
                }
                //System.out.println("head: "+head+" indexLeast: "+indexLeast+" least: " +least);
                //System.out.println("leftTotal: "+leftTotal+" rightTotal: "+rightTotal);
                if (leftTotal > rightTotal) {
                    points+=rightTotal;
                } else {
                    points+=leftTotal;
                }
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
    }
}
