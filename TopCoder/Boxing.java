// Paste me into the FileEdit configuration dialog

import java.util.Arrays;

public class Boxing {

    class Pair implements Comparable<Pair> {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair other) {
            return this.x - other.x == 0 ? this.y - other.y : this.x - other.x;
        }
    }

    public int maxCredit(int[] a, int[] b, int[] c, int[] d, int[] e) {
        int result = 0;

        int[] frontier = new int[5];
        int min = -1;
        int ai, bi, ci, di, ei;
        ai = bi = ci = di = ei = 0;

        while (true) {
            for (; ai < a.length; ai++) {
                if (a[ai] > min) {
                    frontier[0] = a[ai];
                    break;
                }
            }
            for (; bi < b.length; bi++) {
                if (b[bi] > min) {
                    frontier[1] = b[bi];
                    break;
                }
            }

            for (; ci < c.length; ci++) {
                if (c[ci] > min) {
                    frontier[2] = c[ci];
                    break;
                }
            }

            for (; di < d.length; di++) {
                if (d[di] > min) {
                    frontier[3] = d[di];
                    break;
                }
            }

            for (; ei < e.length; ei++) {
                if (e[ei] > min) {
                    frontier[4] = e[ei];
                    break;
                }
            }

            if (a.length == ai)
                frontier[0] = Integer.MAX_VALUE;
            if (b.length == bi)
                frontier[1] = Integer.MAX_VALUE;
            if (c.length == ci)
                frontier[2] = Integer.MAX_VALUE;
            if (d.length == di)
                frontier[3] = Integer.MAX_VALUE;
            if (e.length == ei)
                frontier[4] = Integer.MAX_VALUE;


            Arrays.sort(frontier);
            if (frontier[0] == Integer.MAX_VALUE)
                break;
            if (frontier[2] - frontier[0] <= 1000 && frontier[2] != Integer.MAX_VALUE) {
                result++;
                min = frontier[2];
            }
            else {
                min = frontier[0];
            }
        }

        return result;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BoxingHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                BoxingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BoxingHarness {
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
        }
        else if (correct < total) {
            System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
        }
        else {
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
        }
        else {
            System.err.println("FAILED");
            System.err.println("    Expected: " + formatResult(expected));
            System.err.println("    Received: " + formatResult(received));
            return 0;
        }
    }

    static int runTestCase(int casenum__) {
        switch (casenum__) {
            case 0: {
                int[] a = {1, 2, 3, 4, 5, 6};
                int[] b = {1, 2, 3, 4, 5, 6, 7};
                int[] c = {1, 2, 3, 4, 5, 6};
                int[] d = {0, 1, 2};
                int[] e = {1, 2, 3, 4, 5, 6, 7, 8};
                int expected__ = 6;

                return verifyCase(casenum__, expected__, new Boxing().maxCredit(a, b, c, d, e));
            }
            case 1: {
                int[] a = {100, 200, 300, 1200, 6000};
                int[] b = {};
                int[] c = {900, 902, 1200, 4000, 5000, 6001};
                int[] d = {0, 2000, 6002};
                int[] e = {1, 2, 3, 4, 5, 6, 7, 8};
                int expected__ = 3;

                return verifyCase(casenum__, expected__, new Boxing().maxCredit(a, b, c, d, e));
            }
            case 2: {
                int[] a = {5000, 6500};
                int[] b = {6000};
                int[] c = {6500};
                int[] d = {6000};
                int[] e = {0, 5800, 6000};
                int expected__ = 1;

                return verifyCase(casenum__, expected__, new Boxing().maxCredit(a, b, c, d, e));
            }

            // custom cases

/*      case 3: {
            int[] a                   = ;
			int[] b                   = ;
			int[] c                   = ;
			int[] d                   = ;
			int[] e                   = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Boxing().maxCredit(a, b, c, d, e));
		}*/
/*      case 4: {
            int[] a                   = ;
			int[] b                   = ;
			int[] c                   = ;
			int[] d                   = ;
			int[] e                   = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Boxing().maxCredit(a, b, c, d, e));
		}*/
/*      case 5: {
            int[] a                   = ;
			int[] b                   = ;
			int[] c                   = ;
			int[] d                   = ;
			int[] e                   = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Boxing().maxCredit(a, b, c, d, e));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
