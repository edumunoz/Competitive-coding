// Paste me into the FileEdit configuration dialog

import java.util.LinkedList;
import java.util.Queue;

public class PathFinding {
    public int minTurns(String[] board) {
        int result = -1;

        char[][] grid = new char[board.length][board[0].length()];

        boolean[][][][] visited = new boolean[board.length][board[0].length()][board.length][board[0].length()];
        char[] row;

        Node n;
        int ax = 0, ay = 0, bx = 0, by = 0;
        for (int i = 0; i < board.length; i++) {
            row = board[i].toCharArray();
            for (int j = 0; j < board[0].length(); j++) {
                grid[i][j] = row[j];
                if (row[j] =='A') {
                    ax = i;
                    ay = j;
                }
                if (row[j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        n = new Node(ax, ay, bx, by, 0);

        Queue<Node> q = new LinkedList<Node>();
        q.add(n);
        while (!q.isEmpty()) {
            n = q.remove();

            if (n.ax < 0 || n.ax >= board.length|| n.ay < 0 || n.ay >= board[0].length()) continue;
            if (n.bx < 0 || n.bx >= board.length|| n.by < 0 || n.by >= board[0].length()) continue;
            if (n.ax == n.bx && n.ay == n.by) continue;
            if (grid[n.ax][n.ay] == 'X' || grid[n.bx][n.by] == 'X') continue;
            if (visited[n.ax][n.ay][n.bx][n.by]) continue;

            visited[n.ax][n.ay][n.bx][n.by] = true;

            if (n.ax == bx && n.ay == by && n.bx == ax && n.by == ay)
                return n.steps;

            for (int axdelta = -1; axdelta <= 1; axdelta++) {
                for (int aydelta = -1; aydelta <= 1; aydelta++) {
                    for (int bxdelta = -1; bxdelta <= 1; bxdelta++) {
                        for (int bydelta = -1; bydelta <= 1; bydelta++) {

                            if (n.ax == n.bx + bxdelta && n.ay == n.by + bydelta && n.bx == n.ax + axdelta && n.by == n.ay + aydelta)
                                continue;

                            q.add(new Node(n.ax + axdelta, n.ay + aydelta, n.bx + bxdelta, n.by + bydelta, n.steps+1));
                        }
                    }
                }
            }
        }


        return result;
    }

    class Node {
        int ax, ay, bx, by;
        int steps;

        public Node(int ax, int ay, int bx, int by, int steps) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.steps = steps;
        }
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PathFindingHarness.run_test(-1);
        } else {
            for (int i = 0; i < args.length; ++i)
                PathFindingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PathFindingHarness {
    public static void run_test(int casenum) {
        if (casenum != -1) {
            if (runTestCase(casenum) == -1)
                System.err.println("Illegal input! Test case " + casenum + " does not exist.");
            return;
        }

        int correct = 0, total = 0;
        for (int i = 0; ; ++i) {
            int x = runTestCase(i);
            if (x == -1) {
                if (i >= 100) break;
                continue;
            }
            correct += x;
            ++total;
        }

        if (total == 0) {
            System.err.println("No test cases run.");
        } else if (correct < total) {
            System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
        } else {
            System.err.println("All " + total + " tests passed!");
        }
    }

    static boolean compareOutput(int expected, int result) {
        return expected == result;
    }

    static String formatResult(int res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, int expected, int received) {
        System.err.print("Example " + casenum + "... ");
        if (compareOutput(expected, received)) {
            System.err.println("PASSED");
            return 1;
        } else {
            System.err.println("FAILED");
            System.err.println("    Expected: " + formatResult(expected));
            System.err.println("    Received: " + formatResult(received));
            return 0;
        }
    }

    static int runTestCase(int casenum__) {
        switch (casenum__) {
            case 0: {
                String[] board = {"....",
                        ".A..",
                        "..B.",
                        "...."};
                int expected__ = 2;

                return verifyCase(casenum__, expected__, new PathFinding().minTurns(board));
            }
            case 1: {
                String[] board = {"XXXXXXXXX",
                        "A...X...B",
                        "XXXXXXXXX"};
                int expected__ = -1;

                return verifyCase(casenum__, expected__, new PathFinding().minTurns(board));
            }
            case 2: {
                String[] board = {"XXXXXXXXX",
                        "A.......B",
                        "XXXXXXXXX"};
                int expected__ = -1;

                return verifyCase(casenum__, expected__, new PathFinding().minTurns(board));
            }
            case 3: {
                String[] board = {"XXXXXXXXX",
                        "A.......B",
                        "XXXX.XXXX"};
                int expected__ = 8;

                return verifyCase(casenum__, expected__, new PathFinding().minTurns(board));
            }
            case 4: {
                String[] board = {"...A.XXXXX.....",
                        ".....XXXXX.....",
                        "...............",
                        ".....XXXXX.B...",
                        ".....XXXXX....."};
                int expected__ = 13;

                return verifyCase(casenum__, expected__, new PathFinding().minTurns(board));
            }
            case 5: {
                String[] board = {"AB.................X",
                        "XXXXXXXXXXXXXXXXXXX.",
                        "X..................X",
                        ".XXXXXXXXXXXXXXXXXXX",
                        "X..................X",
                        "XXXXXXXXXXXXXXXXXXX.",
                        "X..................X",
                        ".XXXXXXXXXXXXXXXXXXX",
                        "X..................X",
                        "XXXXXXXXXXXXXXXXXXX.",
                        "X..................X",
                        ".XXXXXXXXXXXXXXXXXXX",
                        "X..................X",
                        "XXXXXXXXXXXXXXXXXXX.",
                        "X..................X",
                        ".XXXXXXXXXXXXXXXXXXX",
                        "X..................X",
                        "XXXXXXXXXXXXXXXXXXX.",
                        "...................X",
                        ".XXXXXXXXXXXXXXXXXXX"};
                int expected__ = 379;

                return verifyCase(casenum__, expected__, new PathFinding().minTurns(board));
            }

            // custom cases

/*      case 6: {
            String[] board            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PathFinding().minTurns(board));
		}*/
/*      case 7: {
            String[] board            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PathFinding().minTurns(board));
		}*/
/*      case 8: {
            String[] board            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PathFinding().minTurns(board));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
