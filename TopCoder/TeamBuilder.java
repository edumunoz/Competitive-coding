// Paste me into the FileEdit configuration dialog

public class TeamBuilder {
    public int[] specialLocations(String[] paths) {
        int[] result = new int[2];

        boolean[][] grid = new boolean[paths.length][paths.length];

        for (int i = 0; i < paths.length; i++) {
            for (int j = 0; j < paths.length; j++) {
                grid[i][j] = paths[i].charAt(j) == '1';
            }
        }

        for (int k = 0; k < grid.length; k++)
            for (int i = 0; i < grid.length; i++)
                for (int j = 0; j < grid.length; j++) {
                    grid[i][j] |= grid[i][k] && grid[k][j];
                }

        for (int i = 0; i < grid.length; i++)
            grid[i][i] = true;

        int reachesAll = 0, allReaches = 0;

        boolean rAll, allR;
        for (int i = 0; i < grid.length; i++) {
            rAll = true;
            allR = true;
            for (int j = 0; j < grid.length; j++) {
                rAll &= grid[i][j];
                allR &= grid[j][i];
            }
            if (rAll)
                reachesAll++;
            if (allR)
                allReaches++;
        }

        result[0] = reachesAll;
        result[1] = allReaches;
        return result;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TeamBuilderHarness.run_test(-1);
        } else {
            for (int i = 0; i < args.length; ++i)
                TeamBuilderHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TeamBuilderHarness {
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

    static boolean compareOutput(int[] expected, int[] result) {
        if (expected.length != result.length) return false;
        for (int i = 0; i < expected.length; ++i) if (expected[i] != result[i]) return false;
        return true;
    }

    static String formatResult(int[] res) {
        String ret = "";
        ret += "{";
        for (int i = 0; i < res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, int[] expected, int[] received) {
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
                String[] paths = {"010", "000", "110"};
                int[] expected__ = {1, 1};

                return verifyCase(casenum__, expected__, new TeamBuilder().specialLocations(paths));
            }
            case 1: {
                String[] paths = {"0010", "1000", "1100", "1000"};
                int[] expected__ = {1, 3};

                return verifyCase(casenum__, expected__, new TeamBuilder().specialLocations(paths));
            }
            case 2: {
                String[] paths = {"01000", "00100", "00010", "00001", "10000"};
                int[] expected__ = {5, 5};

                return verifyCase(casenum__, expected__, new TeamBuilder().specialLocations(paths));
            }
            case 3: {
                String[] paths = {"0110000", "1000100", "0000001", "0010000", "0110000", "1000010", "0001000"};
                int[] expected__ = {1, 3};

                return verifyCase(casenum__, expected__, new TeamBuilder().specialLocations(paths));
            }

            // custom cases

/*      case 4: {
            String[] paths            = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new TeamBuilder().specialLocations(paths));
		}*/
/*      case 5: {
            String[] paths            = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new TeamBuilder().specialLocations(paths));
		}*/
/*      case 6: {
			String[] paths            = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new TeamBuilder().specialLocations(paths));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
