// Paste me into the FileEdit configuration dialog

public class BitwiseEquations {
    static boolean isSet(int n, int idx) {
        return (n >> idx & 1) == 1;
    }

    static long set(long n, int idx) {
        return (1L << idx);
    }

    static int lastOne(int n) {
        int last = -1;
        for (int i = 0; i < 32; i++) {
            if (isSet(n, i))
                last = i;
        }

        return last;
    }

    public long kthPlusOrSolution(int x, int k) {
        int i = 0, j = 0;
        long result = 0;
        int lastOneK = lastOne(k);
        while (i <= lastOneK) {
            if (!isSet(x, j)) {
                if (isSet(k, i))
                    result = set(result, j);
                i++;
            }
            j++;
        }
        return result;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BitwiseEquationsHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                BitwiseEquationsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BitwiseEquationsHarness {
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
                int x = 5;
                int k = 1;
                long expected__ = 2;

                return verifyCase(casenum__, expected__, new BitwiseEquations().kthPlusOrSolution(x, k));
            }
            case 1: {
                int x = 5;
                int k = 5;
                long expected__ = 18;

                return verifyCase(casenum__, expected__, new BitwiseEquations().kthPlusOrSolution(x, k));
            }
            case 2: {
                int x = 10;
                int k = 3;
                long expected__ = 5;

                return verifyCase(casenum__, expected__, new BitwiseEquations().kthPlusOrSolution(x, k));
            }
            case 3: {
                int x = 1;
                int k = 1000000000;
                long expected__ = 2000000000;

                return verifyCase(casenum__, expected__, new BitwiseEquations().kthPlusOrSolution(x, k));
            }

            // custom cases

            case 4: {
                int x = 2000000000;
                int k = 2000000000;
                long expected__ = 16383165351936L;

                return verifyCase(casenum__, expected__, new BitwiseEquations().kthPlusOrSolution(x, k));
            }
            case 5: {
                int x = 1900000001;
                int k = 1900000001;
                long expected__ = 124517645043714L;

                return verifyCase(casenum__, expected__, new BitwiseEquations().kthPlusOrSolution(x, k));
            }
/*      case 6: {
            int x                     = ;
			int k                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new BitwiseEquations().kthPlusOrSolution(x, k));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
