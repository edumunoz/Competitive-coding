// Paste me into the FileEdit configuration dialog

public class TypoCoderDiv1 {
    public static final int boundary = 2200;

    int[] D;
    int[][] cache;

    public int getmax(int[] D, int X) {
        this.D = D;
        cache = new int[boundary][D.length];

        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[0].length; j++)
                cache[i][j] = -1;

        return colorChanges(X, 0);
    }

    public int colorChanges(int currentScore, int contest) {
        // we know that the first time it's ciel, and if it goes to brown, we perform another contest so that
        // currentScore < 2200

        if (contest >= D.length)
            return 0;

        if (cache[currentScore][contest] != -1)
            return cache[currentScore][contest];

        int nextScore = 0;
        int changesLose = -1, changesWin = -1;

        // can win (if can go back to ciel in next contest) or lose
        nextScore = currentScore + D[contest];

        if (nextScore < boundary) {
            // winning no change color
            changesWin = colorChanges(nextScore, contest + 1);
        }

        else {
            // winning makes it brown

            if (contest + 1 < D.length) {
                // compute another contest if there is one
                int nextNextScore = nextScore - Math.min(nextScore, D[contest + 1]);
                if (nextNextScore < boundary) {
                    // can be ciel in next contest after win --> lose
                    changesWin = colorChanges(nextNextScore, contest + 2) + 2;
                }
            }

            if (contest == D.length - 1) {
                // only one change if this was the last contest
                return 1;
            }
        }

        // it loses being ciel, so it stays ciel
        changesLose = colorChanges(currentScore - Math.min(currentScore, D[contest]), contest + 1);

        return cache[currentScore][contest] = Math.max(changesLose, changesWin);
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TypoCoderDiv1Harness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                TypoCoderDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TypoCoderDiv1Harness {
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
                int[] D = {100, 200, 100, 1, 1};
                int X = 2000;
                int expected__ = 3;

                return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
            }
            case 1: {
                int[] D = {0, 0, 0, 0, 0};
                int X = 2199;
                int expected__ = 0;

                return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
            }
            case 2: {
                int[] D = {90000, 80000, 70000, 60000, 50000, 40000, 30000, 20000, 10000};
                int X = 0;
                int expected__ = 1;

                return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
            }
            case 3: {
                int[] D = {1000000000, 1000000000, 10000, 100000, 2202, 1};
                int X = 1000;
                int expected__ = 4;

                return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
            }
            case 4: {
                int[] D = {2048, 1024, 5012, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0};
                int X = 2199;
                int expected__ = 0;

                return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
            }
            case 5: {
                int[] D = {61, 666, 512, 229, 618, 419, 757, 217, 458, 883, 23, 932, 547, 679, 565, 767, 513, 798, 870, 31, 379, 294, 929, 892, 173, 127, 796, 353, 913, 115, 802, 803, 948, 592, 959, 127, 501, 319, 140, 694, 851, 189, 924, 590, 790, 3, 669, 541, 342, 272};
                int X = 1223;
                int expected__ = 29;

                return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
            }
            case 6: {
                int[] D = {34, 64, 43, 14, 58, 30, 2, 16, 90, 58, 35, 55, 46, 24, 14, 73, 96, 13, 9, 42, 64, 36, 89, 42, 42, 64, 52, 68, 53, 76, 52, 54, 23, 88, 32, 52, 28, 96, 70, 32, 26, 3, 23, 78, 47, 23, 54, 30, 86, 32};
                int X = 1328;
                int expected__ = 20;

                return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
            }

            // custom cases

/*      case 7: {
            int[] D                   = ;
			int X                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
		}*/
/*      case 8: {
            int[] D                   = ;
			int X                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
		}*/
/*      case 9: {
            int[] D                   = ;
			int X                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
