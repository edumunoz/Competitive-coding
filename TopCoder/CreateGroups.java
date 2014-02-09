// Paste me into the FileEdit configuration dialog

public class CreateGroups {
    public int minChanges(int[] groups, int minSize, int maxSize) {
        int total = 0;
        for (int n : groups)
            total += n;

        int distribution = total / groups.length;
        if (distribution * groups.length == total) {
            // divisible
            if (maxSize < distribution || minSize > distribution)
                return -1;
        }

        else {
            // ceiling of total / groups.length; so if min is greater or equal to this, it's not possible
            if ((total + groups.length - 1) / groups.length <= minSize)
                return -1;
            if (total / groups.length >= maxSize)
                return -1;
        }

        // it's possible
        int result = 0;
        int needed = 0;
        for (int n : groups) {
            if (n < minSize) {
                result += minSize - n;
                needed += minSize - n;
            }
            if (n > maxSize) {
                needed -= n - maxSize;
            }
        }

        if (needed < 0)
            result += -needed;

        return result;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            CreateGroupsHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                CreateGroupsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class CreateGroupsHarness {
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
                int[] groups = {10, 20};
                int minSize = 10;
                int maxSize = 15;
                int expected__ = 5;

                return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
            }
            case 1: {
                int[] groups = {20, 8, 6};
                int minSize = 10;
                int maxSize = 15;
                int expected__ = 6;

                return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
            }
            case 2: {
                int[] groups = {10, 20, 30};
                int minSize = 1;
                int maxSize = 18;
                int expected__ = -1;

                return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
            }
            case 3: {
                int[] groups = {50, 10, 20, 20, 5};
                int minSize = 15;
                int maxSize = 30;
                int expected__ = 20;

                return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
            }
            case 4: {
                int[] groups = {100, 200, 301};
                int minSize = 200;
                int maxSize = 200;
                int expected__ = -1;

                return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
            }
            case 5: {
                int[] groups = {1, 10, 10};
                int minSize = 9;
                int maxSize = 20;
                int expected__ = -1;

                return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
            }
            case 6: {
                int[] groups = {55, 33, 45, 71, 27, 89, 16, 14, 61};
                int minSize = 33;
                int maxSize = 56;
                int expected__ = 53;

                return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
            }
            case 7: {
                int[] groups = {49, 60, 36, 34, 36, 52, 60, 43, 52, 59};
                int minSize = 45;
                int maxSize = 51;
                int expected__ = 31;

                return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
            }
            case 8: {
                int[] groups = {5, 5, 5, 5, 5};
                int minSize = 5;
                int maxSize = 5;
                int expected__ = 0;

                return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
            }

            // custom cases

/*      case 9: {
            int[] groups              = ;
			int minSize               = ;
			int maxSize               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
		}*/
/*      case 10: {
            int[] groups              = ;
			int minSize               = ;
			int maxSize               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
		}*/
/*      case 11: {
            int[] groups              = ;
			int minSize               = ;
			int maxSize               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new CreateGroups().minChanges(groups, minSize, maxSize));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
