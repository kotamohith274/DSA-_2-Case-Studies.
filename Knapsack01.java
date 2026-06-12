import java.util.*;

public class Knapsack01 {


    // Returns indices (1-based) of items included in optimal subset
    static List<Integer> knapsack01(int[] weights, int[] values, int W) {


        int n = weights.length;


        int[][] dp = new int[n + 1][W + 1];


        // dp[0][*] = 0 by default in Java


        for (int i = 1; i <= n; i++) {


            for (int w = 0; w <= W; w++) {


                // TODO 1: skip-item case
                dp[i][w] = dp[i - 1][w];


                // TODO 2: if item weight <= capacity,
                // consider taking item i
                if (weights[i - 1] <= w) {

                    dp[i][w] = Math.max(
                            dp[i][w],
                            dp[i - 1][w - weights[i - 1]]
                            + values[i - 1]
                    );
                }


                // TODO 3: dp[i][w] already stores
                // maximum of skip and take cases

            }
        }



        // Back-trace:
        // walk from dp[n][W] to determine items taken

        List<Integer> chosen = new ArrayList<>();


        int w = W;


        for (int i = n; i >= 1; i--) {


            // TODO 4: if dp[i][w] != dp[i-1][w],
            // item i was taken

            if (dp[i][w] != dp[i - 1][w]) {


                chosen.add(i); // store 1-based index


                // decrement weight
                w = w - weights[i - 1];
            }
        }


        Collections.reverse(chosen);


        return chosen;
    }



    public static void main(String[] args) {


        int[] weights = {5,8,3,10,4,6,7,2};

        int[] values = {40,50,20,70,30,35,45,15};


        int capacity = 24;



        List<Integer> result =
                knapsack01(weights, values, capacity);



        System.out.println("Items selected:");

        for(int item : result){

            System.out.println("Item " + item);
        }

    }
}