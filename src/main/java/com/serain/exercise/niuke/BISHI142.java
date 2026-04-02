import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BISHI142 {
    static int n, m;
    static int[] s;
    static List<Integer>[] adj;
    static long[][] dp;
    static int[] sz;

    private static void dfs(int u) {
        // 必须选当前课程u
        dp[u][1] = s[u];
        sz[u] = 1;

        for (int v : adj[u]) {
            dfs(v);
            // 将子树v的DP结果合并到u上
            for (int j = Math.min(m + 1, sz[u]); j >= 1; j--) {
                for (int k = 1; k <= sz[v] && j + k <= m + 1; k++) {
                    if (dp[u][j] != -1 && dp[v][k] != -1) {
                        dp[u][j + k] = Math.max(dp[u][j + k], dp[u][j] + dp[v][k]);
                    }
                }
            }
            sz[u] += sz[v];
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        s = new int[n + 1];
        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        dp = new long[n + 1][m + 2];
        for(long[] row : dp) {
            Arrays.fill(row, -1);
        }
        sz = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int p = sc.nextInt();
            s[i] = sc.nextInt();
            adj[p].add(i);
        }

        dfs(0);

        long maxCredits = dp[0][m + 1];
        System.out.println(maxCredits == -1 ? 0 : maxCredits);
    }
}
