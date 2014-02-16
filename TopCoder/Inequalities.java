// Paste me into the FileEdit configuration dialog

import java.util.ArrayList;
import java.util.List;

public class Inequalities {

    class Range {
        int low, high;
        boolean included_low, included_high;

        Range(int low, int high, boolean included_low, boolean included_high) {
            this.low = low;
            this.high = high;
            this.included_low = included_low;
            this.included_high = included_high;
        }

        boolean covers(int n) {
            boolean result = false;
            if (included_low && n == low)
                result = true;
            if (included_high && n == high)
                result = true;
            if (n > low && n < high)
                result = true;
            return result;
        }
    }

    public int maximumSubset(String[] inequalities) {
        //parsing, parse a double the amount to avoid doubles
        List<Range> ranges = new ArrayList<Range>();
        for (String s : inequalities) {
            String relation = s.split(" ")[1];
            int constant = 2 * Integer.parseInt(s.split(" ")[2]);
            Range r = null;
            if (relation.equals("<"))
                r = new Range(-1, constant, true, false);
            else if (relation.equals("<="))
                r = new Range(-1, constant, true, true);
            else if (relation.equals(">"))
                r = new Range(constant, 2001, false, true);
            else if (relation.equals(">="))
                r = new Range(constant, 2001, true, true);
            else if (relation.equals("="))
                r = new Range(constant, constant, true, true);
            ranges.add(r);
        }

        int max = 0;
        for (int i = -1; i < 2002; i++) {
            int local_result = 0;
            for (Range r : ranges) {
                if (r.covers(i))
                    local_result++;
            }
            if (max < local_result)
                max = local_result;
        }

        return max;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            InequalitiesHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                InequalitiesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class InequalitiesHarness {
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
                String[] inequalities = {"X <= 12", "X = 13", "X > 9", "X < 10", "X >= 14"};
                int expected__ = 3;

                return verifyCase(casenum__, expected__, new Inequalities().maximumSubset(inequalities));
            }
            case 1: {
                String[] inequalities = {"X < 0", "X <= 0"};
                int expected__ = 2;

                return verifyCase(casenum__, expected__, new Inequalities().maximumSubset(inequalities));
            }
            case 2: {
                String[] inequalities = {"X = 1", "X = 2", "X = 3", "X > 0"};
                int expected__ = 2;

                return verifyCase(casenum__, expected__, new Inequalities().maximumSubset(inequalities));
            }
            case 3: {
                String[] inequalities = {"X <= 521", "X >= 521", "X = 521", "X > 902", "X > 12", "X <= 1000"};
                int expected__ = 5;

                return verifyCase(casenum__, expected__, new Inequalities().maximumSubset(inequalities));
            }

            // custom cases

/*      case 4: {
            String[] inequalities     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Inequalities().maximumSubset(inequalities));
		}*/
/*      case 5: {
            String[] inequalities     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Inequalities().maximumSubset(inequalities));
		}*/
/*      case 6: {
            String[] inequalities     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Inequalities().maximumSubset(inequalities));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
