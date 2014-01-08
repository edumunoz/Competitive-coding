// Paste me into the FileEdit configuration dialog

import java.util.Comparator;
import java.util.PriorityQueue;

public class KiloManX {
    public int leastShots(String[] damageChart, int[] bossHealth) {
        int result = 0;

        int noWeapons = damageChart.length;
        boolean[] visited = new boolean[1<<noWeapons];
        PriorityQueue<Node> q = new PriorityQueue<Node>(10, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.shots - o2.shots;
            }
        });

        Node n = new Node(0,0);
        q.add(n);

        while (!q.isEmpty()) {
            n = q.remove();
            if (visited[n.weapons]) continue;
            visited[n.weapons] = true;

            if (n.weapons == (1 << noWeapons) - 1) {
                return n.shots;
            }

            for (int i = 0; i < noWeapons; i++) {
                if ((n.weapons >> i & 1) == 1)
                    continue;

                int best = bossHealth[i];
                for (int j = 0; j < noWeapons; j++) {
                    if (i == j) continue;

                    if ((n.weapons >> i & 1) == 1 && damageChart[j].charAt(i) != '0') {
                        int noShotsNeeded = bossHealth[i] / (damageChart[j].charAt(i) - '0');
                        if (bossHealth[i] % (damageChart[j].charAt(i) - '0') != 0) noShotsNeeded++;
                        best = Math.min(best, noShotsNeeded);
                    }
                }
                q.add(new Node(n.weapons | (1 << i), n.shots + best));
            }
        }

        return result;
    }


    class Node {
        int weapons, shots;
        public Node(int weapons, int shots) {
            this.weapons = weapons;
            this.shots = shots;
        }
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            KiloManXHarness.run_test(-1);
        } else {
            for (int i = 0; i < args.length; ++i)
                KiloManXHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class KiloManXHarness {
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
                String[] damageChart = {"070", "500", "140"};
                int[] bossHealth = {150, 150, 150};
                int expected__ = 218;

                return verifyCase(casenum__, expected__, new KiloManX().leastShots(damageChart, bossHealth));
            }
            case 1: {
                String[] damageChart = {"1542", "7935", "1139", "8882"};
                int[] bossHealth = {150, 150, 150, 150};
                int expected__ = 205;

                return verifyCase(casenum__, expected__, new KiloManX().leastShots(damageChart, bossHealth));
            }
            case 2: {
                String[] damageChart = {"07", "40"};
                int[] bossHealth = {150, 10};
                int expected__ = 48;

                return verifyCase(casenum__, expected__, new KiloManX().leastShots(damageChart, bossHealth));
            }
            case 3: {
                String[] damageChart = {"198573618294842",
                        "159819849819205",
                        "698849290010992",
                        "000000000000000",
                        "139581938009384",
                        "158919111891911",
                        "182731827381787",
                        "135788359198718",
                        "187587819218927",
                        "185783759199192",
                        "857819038188122",
                        "897387187472737",
                        "159938981818247",
                        "128974182773177",
                        "135885818282838"};
                int[] bossHealth = {157, 1984, 577, 3001, 2003, 2984, 5988, 190003, 9000, 102930, 5938, 1000000, 1000000, 5892, 38};
                int expected__ = 260445;

                return verifyCase(casenum__, expected__, new KiloManX().leastShots(damageChart, bossHealth));
            }
            case 4: {
                String[] damageChart = {"02111111", "10711111", "11071111", "11104111",
                        "41110111", "11111031", "11111107", "11111210"};
                int[] bossHealth = {28, 28, 28, 28, 28, 28, 28, 28};
                int expected__ = 92;

                return verifyCase(casenum__, expected__, new KiloManX().leastShots(damageChart, bossHealth));
            }

            // custom cases

/*      case 5: {
            String[] damageChart      = ;
			int[] bossHealth          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new KiloManX().leastShots(damageChart, bossHealth));
		}*/
/*      case 6: {
			String[] damageChart      = ;
			int[] bossHealth          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new KiloManX().leastShots(damageChart, bossHealth));
		}*/
/*      case 7: {
			String[] damageChart      = ;
			int[] bossHealth          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new KiloManX().leastShots(damageChart, bossHealth));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
