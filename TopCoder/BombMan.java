// Paste me into the FileEdit configuration dialog

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BombMan {
    boolean[][] visited;
    String[] maze;

    public int shortestPath(String[] maze, int bombs) {
        int result = -1;
        this.maze = maze;
        visited = new boolean[maze.length][maze[0].length()];
        Node n = null;
        int endx = 0, endy = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length(); j++) {
                if (maze[i].charAt(j) == 'B')
                    n = new Node(i, j, 0, bombs);
                else if (maze[i].charAt(j) == 'E') {
                    endx = i;
                    endy = j;
                }
            }
        }

        Queue<Node> q = new PriorityQueue<Node>(1, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.time - o2.time;
            }
        });

        q.add(n);
        while (!q.isEmpty()) {
            n = q.remove();
            if (n.x == endx && n.y == endy)
                return n.time;

            if (visited[n.x][n.y])
                continue;
            visited[n.x][n.y] = true;

            pushToQueue(q, n.x + 1, n.y, n.time, n.bombs);
            pushToQueue(q, n.x - 1, n.y, n.time, n.bombs);
            pushToQueue(q, n.x, n.y + 1, n.time, n.bombs);
            pushToQueue(q, n.x, n.y - 1, n.time, n.bombs);
        }

        return result;
    }

    void pushToQueue(Queue<Node> q, int x, int y, int prev_time, int bombs) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length())
            return;

        if (maze[x].charAt(y) == '#') {
            if (bombs > 0)
                q.add(new Node(x, y, prev_time + 3, bombs - 1));
        } else
            q.add(new Node(x, y, prev_time + 1, bombs));
    }

    class Node {
        int x, y, time, bombs;

        Node(int x, int y, int time, int bombs) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.bombs = bombs;
        }
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BombManHarness.run_test(-1);
        } else {
            for (int i = 0; i < args.length; ++i)
                BombManHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BombManHarness {
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
                String[] maze = {".....B.",
                        ".#####.",
                        ".#...#.",
                        ".#E#.#.",
                        ".###.#.",
                        "......."};
                int bombs = 1;
                int expected__ = 8;

                return verifyCase(casenum__, expected__, new BombMan().shortestPath(maze, bombs));
            }
            case 1: {
                String[] maze = {"B.#.#.#...E"};
                int bombs = 2;
                int expected__ = -1;

                return verifyCase(casenum__, expected__, new BombMan().shortestPath(maze, bombs));
            }
            case 2: {
                String[] maze = {"..#####",
                        "B.#####",
                        "..#####",
                        "#######",
                        "####...",
                        "####.E."};
                int bombs = 4;
                int expected__ = 17;

                return verifyCase(casenum__, expected__, new BombMan().shortestPath(maze, bombs));
            }
            case 3: {
                String[] maze = {".#.#.#.#B#...#.#...#.#...#.#...#.#...#.#.#.......",
                        ".#.#.#.#.#.###.###.#.###.#.#.###.###.#.#.#.###.##",
                        ".#.#.#...#.#.#.#.#.#...#.....#.#.#...#...#.#.#...",
                        ".#.#.###.#.#.#.#.#.###.#.#####.#.###.###.#.#.###.",
                        ".............#.#...#...#.....#.#.#...#.#.#.....#.",
                        "##.#######.###.#.#####.#.#####.#.###.#.#.#.#.####",
                        ".#.#.....#...#...#.#...#...#.#.#...#...#...#.....",
                        ".#######.#.#####.#.#.#.#.###.#.###.#.#####.#.####",
                        ".#.#.#.#...#.#.#.#.#.#.......#...#.#...#.#.#.....",
                        ".#.#.#.###.#.#.#.#.#####.#####.###.###.#.#.######",
                        ".....#...#.#...#...#...#...#...#...#.#.#.........",
                        "####.###.#.###.###.#.###.#.#.###.###.#.#.########",
                        ".......#.........#.#.#.#.#.#.#.#.........#...#...",
                        ".#.###.#########.#.#.#.#.###.#.#####.#.#.#.###.##",
                        ".#.#.........#.#.#.#.#.....#.#.#.....#.#.........",
                        "############.#.#.#.#.#.#####.#.#.################",
                        ".#...........#...#.#.#.#...#.#.#...#.#.#.....#...",
                        ".#####.#####.###.#.#.#.#.###.#.#.###.#.#.#####.##",
                        ".......#...#.#.#.....#...#...#.#.#.#.#...........",
                        "##########.#.#.#####.#.###.###.#.#.#.#.##########",
                        ".....#...#.....#.#...#.......#.#...#.......#.....",
                        "##.#.###.#.###.#.#.#.#.#####.#.#.###.#######.####",
                        "...#...#...#.......#.....#.#...#...#.......#.....",
                        "####.#.#.#########.#.###.#.#####.###.#.#######.##",
                        ".#...#...#.........#.#.....#.........#.#.#.#.....",
                        ".#####.#.#.###.#######.#.###.#.#########.#.#.####",
                        ".......#.#.#...#.......#.....#.#.#.......#.#.#.#.",
                        "########.#.#.#.#####.#.###.#.###.#.#######.#.#.#.",
                        ".........#.#.#.#.....#...#.#.........#.#.........",
                        "################.#.#.#.#.#.#.#.#######.#.########",
                        ".................#.#.#.#.#.#.#...........#.......",
                        "########.#####.#.###.#.#.#####.###.#.#####.###.##",
                        ".........#...#.#...#.#.#...#.....#.#.........#...",
                        ".#####.#####.#.###.#.###.#.#.#.#.#.#####.#.###.#.",
                        ".#.....#.........#.#.#...#.#.#.#.#.#.....#...#.#.",
                        "####.#####.###.#.#.#.#.#.#.###.###.#.#.#.#.#####.",
                        ".....#.....#.#.#.#.#.#.#.#.#...#...#.#.#.#...#...",
                        "####.#.#.###.#.#.###.#.###.#.#.#####.#.#.#.######",
                        ".....#.#.#.#...#...#.#...#.#.#...#...#.#.#.......",
                        "##########.#.#.#.#####.###.#.#.###.#.###.#####.##",
                        "...#.#...#...#.#.....#.#...#.#...#.#.#.......#...",
                        ".#.#.#.#.#.#.#.#.#.#.###.#.#########.###.#.#.#.#.",
                        ".#.#...#...#.#.#.#.#...#.#...#.......#...#.#.#.#.",
                        "##.###.#.#.###.#.#.#.#.#####.#.#.#.###.#.########",
                        ".......#.#...#.#.#.#.#.#.....#.#.#...#.#.........",
                        "####.#######.#.#####.#.###.#.#.###.#.#.#.########",
                        "E..#.......#.#.....#.#.#.#.#.#.#...#.#.#.........",
                        "##.#.#.#.###.###.###.###.#.#.###.#.#.#.#.#######.",
                        ".....#.#...#.#.....#.#.....#...#.#.#.#.#.....#..."};
                int bombs = 3;
                int expected__ = 76;

                return verifyCase(casenum__, expected__, new BombMan().shortestPath(maze, bombs));
            }

            // custom cases

/*      case 4: {
            String[] maze             = ;
			int bombs                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new BombMan().shortestPath(maze, bombs));
		}*/
/*      case 5: {
            String[] maze             = ;
			int bombs                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new BombMan().shortestPath(maze, bombs));
		}*/
/*      case 6: {
            String[] maze             = ;
			int bombs                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new BombMan().shortestPath(maze, bombs));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
