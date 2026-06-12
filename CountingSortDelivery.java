import java.util.*;

public class CountingSortDelivery {

    static class Delivery {
        int over, ball;
        int batsmanId, bowlerId, runs;

        Delivery(int over, int ball) {
            this.over = over;
            this.ball = ball;
        }

        Delivery(int over, int ball, int batsmanId, int bowlerId, int runs) {
            this.over = over;
            this.ball = ball;
            this.batsmanId = batsmanId;
            this.bowlerId = bowlerId;
            this.runs = runs;
        }
    }


    // Stable counting sort by over field
    // K = 50
    static Delivery[] countingSortByOver(Delivery[] in) {

        final int K = 50;

        int[] count = new int[K + 1];


        // TODO 1: count occurrences
        for (Delivery d : in) {
            count[d.over]++;
        }


        // TODO 2: prefix sum
        for (int i = 1; i <= K; i++) {
            count[i] = count[i] + count[i - 1];
        }


        Delivery[] out = new Delivery[in.length];


        // TODO 3: reverse order for stability
        for (int i = in.length - 1; i >= 0; i--) {

            Delivery d = in[i];

            out[count[d.over] - 1] = d;

            count[d.over]--;
        }


        return out;
    }


    public static void main(String[] args) {


        Delivery[] arr = {

            new Delivery(2,4),
            new Delivery(1,1),
            new Delivery(3,6),
            new Delivery(1,5),
            new Delivery(2,2),
            new Delivery(3,1),
            new Delivery(1,3),
            new Delivery(2,6),
            new Delivery(3,4),
            new Delivery(1,2)

        };


        Delivery[] result = countingSortByOver(arr);


        System.out.println("Sorted by over:");

        for(Delivery d : result) {

            System.out.println("(" 
                + d.over + "," 
                + d.ball + ")");
        }
    }
}