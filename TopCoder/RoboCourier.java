// Paste me into the FileEdit configuration dialog

public class RoboCourier {
    int[] dx = {0, 1, 1, 0, -1, -1};
    int[] dy = {1, 1, -1, -1, -1, 1};

    public int timeToDeliver(String[] path) {
        int result = 0;



        return result;
    }

    class Node {
        int time;
        int x, y;
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            RoboCourierHarness.run_test(-1);
        } else {
            for (int i = 0; i < args.length; ++i)
                RoboCourierHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class RoboCourierHarness {
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
                String[] path = {"FRRFLLFLLFRRFLF"};
                int expected__ = 15;

                return verifyCase(casenum__, expected__, new RoboCourier().timeToDeliver(path));
            }
            case 1: {
                String[] path = {"RFLLF"};
                int expected__ = 17;

                return verifyCase(casenum__, expected__, new RoboCourier().timeToDeliver(path));
            }
            case 2: {
                String[] path = {"FLFRRFRFRRFLLFRRF"};
                int expected__ = 0;

                return verifyCase(casenum__, expected__, new RoboCourier().timeToDeliver(path));
            }
            case 3: {
                String[] path = {"FFFFFFFFFRRFFFFFFRRFFFFF",
                        "FLLFFFFFFLLFFFFFFRRFFFF"};
                int expected__ = 44;

                return verifyCase(casenum__, expected__, new RoboCourier().timeToDeliver(path));
            }
            case 4: {
                String[] path = {"RFLLFLFLFRFRRFFFRFFRFFRRFLFFRLRRFFLFFLFLLFRFLFLRFF",
                        "RFFLFLFFRFFLLFLLFRFRFLRLFLRRFLRFLFFLFFFLFLFFRLFRLF",
                        "LLFLFLRLRRFLFLFRLFRF"};
                int expected__ = 24;

                return verifyCase(casenum__, expected__, new RoboCourier().timeToDeliver(path));
            }
            case 5: {
                String[] path = {"LLFLFRLRRLRFFLRRRRFFFLRFFRRRLLFLFLLRLRFFLFRRFFFLFL",
                        "RLFFRRLRLRRFFFLLLRFRLLRFFLFRLFRRFRRRFRLRLRLFFLLFLF",
                        "FRFLRFRRLLLRFFRRRLRFLFRRFLFFRLFLFLFRLLLLFRLLRFLLLF",
                        "FFLFRFRRFLLFFLLLFFRLLFLRRFRLFFFRRFFFLLRFFLRFRRRLLR",
                        "FFFRRLLFLLRLFRRLRLLFFFLFLRFFRLRLLFLRLFFLLFFLLFFFRR",
                        "LRFRRFLRRLRRLRFFFLLLLRRLRFFLFRFFRLLRFLFRRFLFLFFLFR",
                        "RFRRLRRFLFFFLLRFLFRRFRFLRLRLLLLFLFFFLFRLLRFRLFRLFR",
                        "LLFLFRLFFFFFFFRRLRLRLLRFLRLRRRRRRRRLFLFLFLRFLFRLFF",
                        "RLFRRLLRRRRFFFRRRLLLLRRLFFLLLLLRFFFFRFRRLRRRFFFLLF",
                        "FFFFLRRLRFLLRRLRLRFRRRRLFLLRFLRRFFFRFRLFFRLLFFRRLL"};
                int expected__ = 169;

                return verifyCase(casenum__, expected__, new RoboCourier().timeToDeliver(path));
            }

            // custom cases

/*      case 6: {
            String[] path             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new RoboCourier().timeToDeliver(path));
		}*/
/*      case 7: {
			String[] path             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new RoboCourier().timeToDeliver(path));
		}*/
/*      case 8: {
			String[] path             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new RoboCourier().timeToDeliver(path));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
