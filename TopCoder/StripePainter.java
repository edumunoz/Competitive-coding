// Paste me into the FileEdit configuration dialog

import java.util.Arrays;

public class StripePainter {
    public int minStrokes(String stripes) {
        // DP[a][b] is minimum no. strokes to paint from stripes[a] to stripes[b]
        int[][] DP = new int[stripes.length()][stripes.length()];

        for (int i = 0; i < stripes.length(); i++) {
            Arrays.fill(DP[i], Integer.MAX_VALUE);
            DP[i][i] = 1;
        }

        for (int length = 1; length < stripes.length(); length++)
            for (int j = 0; j + length < stripes.length(); j++)
                for (int k = j; k < j + length; k++) {
                    DP[j][j + length] = Math.min(DP[j][j + length],
                                                 DP[j][k] + DP[k + 1][j + length] - (stripes.charAt(j) == stripes.charAt(j + length) ? 1 : 0));
                }

        return DP[0][stripes.length() - 1];
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            StripePainterHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                StripePainterHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class StripePainterHarness {
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
                String stripes = "RGBGR";
                int expected__ = 3;

                return verifyCase(casenum__, expected__, new StripePainter().minStrokes(stripes));
            }
            case 1: {
                String stripes = "RGRG";
                int expected__ = 3;

                return verifyCase(casenum__, expected__, new StripePainter().minStrokes(stripes));
            }
            case 2: {
                String stripes = "ABACADA";
                int expected__ = 4;

                return verifyCase(casenum__, expected__, new StripePainter().minStrokes(stripes));
            }
            case 3: {
                String stripes = "AABBCCDDCCBBAABBCCDD";
                int expected__ = 7;

                return verifyCase(casenum__, expected__, new StripePainter().minStrokes(stripes));
            }
            case 4: {
                String stripes = "BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD";
                int expected__ = 26;

                return verifyCase(casenum__, expected__, new StripePainter().minStrokes(stripes));
            }

            // custom cases

/*      case 5: {
            String stripes            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new StripePainter().minStrokes(stripes));
		}*/
/*      case 6: {
            String stripes            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new StripePainter().minStrokes(stripes));
		}*/
/*      case 7: {
            String stripes            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new StripePainter().minStrokes(stripes));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
