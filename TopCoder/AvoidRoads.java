// Paste me into the FileEdit configuration dialog

public class AvoidRoads {

    public long numWays(int width, int height, String[] bad) {

        long[][] W = new long[width + 1][height + 1];

        boolean[][][][] cantPass = new boolean[width + 1][height + 1][width + 1][height + 1];

        for (String s : bad) {
            String[] coordinates = s.split(" ");
            int x1 = Math.min(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[2]));
            int x2 = Math.max(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[2]));
            int y1 = Math.min(Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[3]));
            int y2 = Math.max(Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[3]));

            cantPass[x1][y1][x2][y2] = true;
        }


        if (!cantPass[width - 1][height][width][height])
            W[width - 1][height] = 1;

        if (!cantPass[width][height - 1][width][height])
            W[width][height - 1] = 1;

        for (int x = width; x > -1; x--)
            for (int y = height; y > -1; y--) {
                if (x == width && y == height)
                    continue;
                if (x < width && !cantPass[x][y][x + 1][y])
                    W[x][y] += W[x + 1][y];
                if (y < height && !cantPass[x][y][x][y + 1])
                    W[x][y] += W[x][y + 1];
            }

        return W[0][0];
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            AvoidRoadsHarness.run_test(-1);
        } else {
            for (int i = 0; i < args.length; ++i)
                AvoidRoadsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class AvoidRoadsHarness {
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

    static boolean compareOutput(long expected, long result) {
        return expected == result;
    }

    static String formatResult(long res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, long expected, long received) {
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
                int width = 6;
                int height = 6;
                String[] bad = {"0 0 0 1", "6 6 5 6"};
                long expected__ = 252;

                return verifyCase(casenum__, expected__, new AvoidRoads().numWays(width, height, bad));
            }
            case 1: {
                int width = 1;
                int height = 1;
                String[] bad = {};
                long expected__ = 2;

                return verifyCase(casenum__, expected__, new AvoidRoads().numWays(width, height, bad));
            }
            case 2: {
                int width = 35;
                int height = 31;
                String[] bad = {};
                long expected__ = 6406484391866534976L;

                return verifyCase(casenum__, expected__, new AvoidRoads().numWays(width, height, bad));
            }
            case 3: {
                int width = 2;
                int height = 2;
                String[] bad = {"0 0 1 0", "1 2 2 2", "1 1 2 1"};
                long expected__ = 0;

                return verifyCase(casenum__, expected__, new AvoidRoads().numWays(width, height, bad));
            }

            // custom cases

/*      case 4: {
            int width                 = ;
			int height                = ;
			String[] bad              = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new AvoidRoads().numWays(width, height, bad));
		}*/
/*      case 5: {
            int width                 = ;
			int height                = ;
			String[] bad              = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new AvoidRoads().numWays(width, height, bad));
		}*/
/*      case 6: {
            int width                 = ;
			int height                = ;
			String[] bad              = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new AvoidRoads().numWays(width, height, bad));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
