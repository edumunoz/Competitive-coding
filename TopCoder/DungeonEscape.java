// Paste me into the FileEdit configuration dialog

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DungeonEscape {
    char[][] up, down, east, west;
    int noFloors, noEast;
    boolean[][] visited;

    char[][] StringArToCharArAr(String[] ar) {
        char[][] result = new char[ar.length][ar[0].length()];
        for (int i = 0; i < ar.length; i++) {
            result[i] = ar[i].toCharArray();
        }

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (result[i][j] != 'x') {
                    result[i][j] = (char) (result[i][j] - '0');
                }
            }
        }
        return result;
    }

    public int exitTime(String[] up, String[] down, String[] east, String[] west, int startLevel, int startEasting) {
        int result = -1;
        this.up = StringArToCharArAr(up);
        this.down = StringArToCharArAr(down);
        this.east = StringArToCharArAr(east);
        this.west = StringArToCharArAr(west);
        this.noFloors = up.length;
        this.noEast = up[0].length();
        this.visited = new boolean[noFloors][noEast];

        Node n = new Node(startLevel, startEasting, 0);

        Queue<Node> q = new PriorityQueue<Node>(10, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.time - o2.time;
            }
        });

        q.add(n);

        while (!q.isEmpty()) {
            n = q.remove();
            // End condition
            if (n.level == -1) return n.time;

            if (visited[n.level][n.east]) continue;
            visited[n.level][n.east] = true;


            if (noFloors - n.time / this.noEast <= n.level) {
                continue;
            }
/*
            // don't consider already considered nodes
            boolean fup = true, fdown = true, feast = true, fwest = true;
            if (n.level + 1 < noFloors && this.up[n.level + 1][n.east] != 'x')
                fup = false;

            if (n.level - 1 >= 0 && this.down[n.level - 1][n.east] != 'x')
                fdown = false;

            if (n.east - 1 >= 0 && this.east[n.level][n.east - 1] != 'x')
                feast = false;

            if (n.east + 1 < noEast && this.west[n.level][n.east + 1] != 'x')
                fwest = false;

            if (fup && fdown && feast && fwest)
                continue;
*/

/*
            // Mark as visited
            if (n.level + 1 < noFloors)
                this.up[n.level + 1][n.east] = 'x';
            if (n.level - 1 >= 0)
                this.down[n.level - 1][n.east] = 'x';
            if (n.east - 1 >= 0)
                this.east[n.level][n.east - 1] = 'x';
            if (n.east + 1 < noEast)
                this.west[n.level][n.east + 1] = 'x';
*/


            // Only add node if can go there
            if (this.up[n.level][n.east] != 'x') {
                addToQueue(q, n.level - 1, n.east, n.time + this.up[n.level][n.east]);
            }
            if (this.down[n.level][n.east] != 'x') {
                addToQueue(q, n.level + 1, n.east, n.time + this.down[n.level][n.east]);
            }
            if (this.east[n.level][n.east] != 'x') {
                addToQueue(q, n.level, n.east + 1, n.time + this.east[n.level][n.east]);
            }
            if (this.west[n.level][n.east] != 'x') {
                addToQueue(q, n.level, n.east - 1, n.time + this.west[n.level][n.east]);
            }
        }

        return result;
    }

    void addToQueue(Queue<Node> q, int level, int east, int time) {
        {
            if (level < -1 || east < 0 || level >= this.noFloors || east >= this.noEast)
                return;
            q.add(new Node(level, east, time));
        }
    }

    class Node {
        int level, east, time;

        Node(int level, int east, int time) {
            this.level = level;
            this.east = east;
            this.time = time;
        }
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            DungeonEscapeHarness.run_test(-1);
        } else {
            for (int i = 0; i < args.length; ++i)
                DungeonEscapeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class DungeonEscapeHarness {
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
                String[] up = {"0x4",
                        "0x3",
                        "0x3"};
                String[] down = {"0x9",
                        "009",
                        "0x9"};
                String[] east = {"0x9",
                        "1x9",
                        "009"};
                String[] west = {"0x9",
                        "0x0",
                        "009"};
                int startLevel = 2;
                int startEasting = 2;
                int expected__ = 10;

                return verifyCase(casenum__, expected__, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
            }
            case 1: {
                String[] up = {"xxxxxxxxx1",
                        "1xxxxxxxxx",
                        "xxxxxxxxx1"};
                String[] down = {"xxxxxxxxxx",
                        "xxxxxxxxxx",
                        "xxxxxxxxxx"};
                String[] east = {"1111111111",
                        "xxxxxxxxxx",
                        "1111111111"};
                String[] west = {"xxxxxxxxxx",
                        "1111111111",
                        "xxxxxxxxxx"};
                int startLevel = 2;
                int startEasting = 0;
                int expected__ = 30;

                return verifyCase(casenum__, expected__, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
            }
            case 2: {
                String[] up = {"xxxxxxxxx1",
                        "xxxx999991",
                        "x999999991"};
                String[] down = {"1111111111",
                        "1111111111",
                        "1111111111"};
                String[] east = {"1111122242",
                        "2222222241",
                        "2111111111"};
                String[] west = {"xxxxxxxxx1",
                        "1111111111",
                        "xxxxxxxxx1"};
                int startLevel = 2;
                int startEasting = 0;
                int expected__ = -1;

                return verifyCase(casenum__, expected__, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
            }
            case 3: {
                String[] up = {"1x2x3x4x5x6x7x8x9",
                        "00000000000000000",
                        "98765432223456789",
                        "12345678987654321"};
                String[] down = {"00000000000000000",
                        "00000000000000000",
                        "00000000000000000",
                        "00000000000000000"};
                String[] east = {"xxxxxxxxxxxxxxxxx",
                        "xxxxxxxxxxxxxxxxx",
                        "22222222222222222",
                        "33333333333333333"};
                String[] west = {"xxxxxxxxxxxxxxxxx",
                        "xxxxxxxxxxxxxxxxx",
                        "22222222222222222",
                        "33333333333333333"};
                int startLevel = 3;
                int startEasting = 12;
                int expected__ = 17;

                return verifyCase(casenum__, expected__, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
            }

            // custom cases

      case 4: {
            String[] up               = {"1x7",
                    "x43",
                    "55x",
                    "81x",
                    "x9x",
                    "764", "834", "66x", "67x", "4x6", "41x", "x9x", "68x", "13x"};
			String[] down             = {"082", "x4x", "816", "xx4", "8x0", "26x", "x4x", "3x7", "60x", "x21", "4x6", "x5x", "x04", "x41"};
			String[] east             = {"9xx", "947", "x23", "61x", "004", "90x", "xxx", "280", "1x5", "550", "2xx", "x6x", "7x9", "2x1"};
			String[] west             = {"367", "12x", "935", "584", "xx5", "xxx", "206", "x18", "258", "210", "x83", "xxx", "298", "52x"};
			int startLevel            = 7;
			int startEasting          = 0;
			int expected__            = 46;

			return verifyCase(casenum__, expected__, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
		}
/*      case 5: {
            String[] up               = ;
			String[] down             = ;
			String[] east             = ;
			String[] west             = ;
			int startLevel            = ;
			int startEasting          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
		}*/
/*      case 6: {
            String[] up               = ;
			String[] down             = ;
			String[] east             = ;
			String[] west             = ;
			int startLevel            = ;
			int startEasting          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
