// Paste me into the FileEdit configuration dialog

import java.util.ArrayList;

public class Circuits {
    int[][] g;
    int size;

    public int howLong(String[] connects, String[] costs) {
        int result = 0;

        size = connects.length;
        g = new int[size][size];

        String[] connections;
        String[] weights;

        // fill in g
        for (int i = 0; i < size; i++) {
            if (connects[i] == "") continue;
            connections = connects[i].split(" ");
            weights = costs[i].split(" ");

            for (int j = 0; j < connections.length; j++) {
                g[i][Integer.parseInt(connections[j])] = Integer.parseInt(weights[j]);
            }
        }

        for (int i = 0; i < size; i++) {
            result = max(result, dfs(i, -1));
        }

        return result;
    }

    int dfs(int v, int father) {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (i != father && g[v][i] != 0) {
                max = max(max, dfs(i,v) + g[v][i]);
            }
        }
        return max;
    }

    int max(int x, int y) {
        if (x > y) return x;
        else return y;
    }

}



// Powered by FileEdit
// Powered by moj 4.18 [modified TZTester]
// Powered by CodeProcessor
