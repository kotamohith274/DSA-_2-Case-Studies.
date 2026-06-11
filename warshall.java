import java.util.ArrayList;
import java.util.List;

public class warshall {

    private static final int INF = 10000000;

    // 1. Your warshall method (Now being used in main!)
    static int[][] warshall(int n, int[][] edges, int[][] next) {
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                    next[i][j] = j; 
                } else if (edges[i][j] != INF && edges[i][j] != 0) { 
                    dp[i][j] = edges[i][j];
                    next[i][j] = j;
                } else {
                    dp[i][j] = INF;
                    next[i][j] = -1; 
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] != INF && dp[k][j] != INF) {
                        if (dp[i][k] + dp[k][j] < dp[i][j]) {
                            dp[i][j] = dp[i][k] + dp[k][j];
                            next[i][j] = next[i][k]; 
                        }
                    }
                }
            }
        }
        return dp;
    }

    // 2. Your reconstructPath method (Now being used in main!)
    static List<Integer> reconstructPath(int[][] next, int i, int j) {
        List<Integer> result = new ArrayList<>();
        if (next == null || next[i][j] == -1) {
            return result;
        }
        int u = i;
        while (u != j) {
            result.add(u);
            u = next[u][j];
        }
        result.add(j);
        return result;
    }

    // 3. THE FIX: The main method that actually calls them
    public static void main(String[] args) {
        int n = 4; // A graph with 4 vertices (0, 1, 2, 3)
        
        // Define a sample graph adjacency matrix
        int[][] edges = {
            {0,   3,   INF, 7},
            {8,   0,   2,   INF},
            {INF, INF, 0,   1},
            {2,   INF, INF, 0}
        };
        
        // Create the matrix to hold path reconstruction tracking
        int[][] next = new int[n][n]; 
        
        // This explicitly "uses" the warshall method
        int[][] shortestDistances = warshall(n, edges, next);
        
        // This explicitly "uses" the reconstructPath method
        List<Integer> path = reconstructPath(next, 0, 2); 
        
        // Print results to the console
        System.out.println("Shortest distance from 0 to 2 is: " + shortestDistances[0][2]);
        System.out.println("The exact path taken is: " + path);
    }
}