// Paste me into the FileEdit configuration dialog

public class SplitIntoPairs {
    public int makepairs(int[] A, int X) {
        int result = 0;
        long negativeCount = 0, greatestNegative = Integer.MIN_VALUE, smallestPositive = Integer.MAX_VALUE;

        for (int n : A) {
            if (n < 0) {
                negativeCount++;
                if (n > greatestNegative)
                    greatestNegative = n;
            }

            if (n >= 0 && n < smallestPositive)
                smallestPositive = n;
        }

        if (negativeCount % 2 == 0)
            result = A.length / 2;

        else if (greatestNegative * smallestPositive >= X) {
            result = A.length / 2;
        }
        else
            result = A.length / 2 - 1;

        return result;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SplitIntoPairsHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                SplitIntoPairsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SplitIntoPairsHarness {
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
                int[] A = {2, -1};
                int X = -1;
                int expected__ = 0;

                return verifyCase(casenum__, expected__, new SplitIntoPairs().makepairs(A, X));
            }
            case 1: {
                int[] A = {1, -1};
                int X = -1;
                int expected__ = 1;

                return verifyCase(casenum__, expected__, new SplitIntoPairs().makepairs(A, X));
            }
            case 2: {
                int[] A = {-5, -4, 2, 3};
                int X = -1;
                int expected__ = 2;

                return verifyCase(casenum__, expected__, new SplitIntoPairs().makepairs(A, X));
            }
            case 3: {
                int[] A = {5, -7, 8, -2, -5, 3};
                int X = -7;
                int expected__ = 3;

                return verifyCase(casenum__, expected__, new SplitIntoPairs().makepairs(A, X));
            }
            case 4: {
                int[] A = {3, 4, 5, 6, 6, -6, -4, -10, -1, -9};
                int X = -2;
                int expected__ = 4;

                return verifyCase(casenum__, expected__, new SplitIntoPairs().makepairs(A, X));
            }
            case 5: {
                int[] A = {1000000, 1000000};
                int X = -5;
                int expected__ = 1;

                return verifyCase(casenum__, expected__, new SplitIntoPairs().makepairs(A, X));
            }

            // custom cases

      case 6: {
            int[] A                   = {-7726273, -198566, 9681506, -6792315, -540917, -4861632, -4982458, -1383709, -6158006, 4166470, -6812236, -624690, 5158649, -9032251, -8845697, 4151603, 6274931, -8029271, -5157899, 6663477, -1398145, -4257609, 145272, -5237570, 861762, -4839969, -2853810, 577517, -941855, 707825, -9448768, -438974, -2454848, 9216156, 5254895, -923375, 440146, 3309157, -9364361, 8771003};
			int X                     =  -2352133;
			int expected__            = 19;

			return verifyCase(casenum__, expected__, new SplitIntoPairs().makepairs(A, X));
		}
      case 7: {
            int[] A                   = {-5, 140, 94, -45, -136, -139, 193, 125, -117, 97, -87, -111, 199, 0, 43, 135, -37, 32, -62, 170, -50, -71};
			int X                     = -1;
			int expected__            = 11;

			return verifyCase(casenum__, expected__, new SplitIntoPairs().makepairs(A, X));
		}
/*      case 8: {
            int[] A                   = ;
			int X                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SplitIntoPairs().makepairs(A, X));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
