// Paste me into the FileEdit configuration dialog

public class AutoLoan {
    public double interestRate(double price, double monthlyPayment, int loanTerm) {
        double lo = 0.0, hi = 100.0;
        while ((hi - lo) >= 1.0E-9) {
            double med = lo + (hi - lo) / 2;
            double monthlyInterest = med / 1200;
            double balance = price;
            int months = 0;

            if (balance * monthlyInterest > monthlyPayment) {
                balance = 1;
                // to skip the while loop
                months = loanTerm + 1;
            }

            while (months < loanTerm) {
                balance = balance * (1 + monthlyInterest) - monthlyPayment;
                months++;
            }

            if (balance >= 0) {
                hi = med;
            }
            else {
                lo = med;
            }
        }
        return lo;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            AutoLoanHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                AutoLoanHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class AutoLoanHarness {
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

    static final double MAX_DOUBLE_ERROR = 1E-9;

    static boolean compareOutput(double expected, double result) {
        if (Double.isNaN(expected)) {
            return Double.isNaN(result);
        }
        else if (Double.isInfinite(expected)) {
            if (expected > 0) {
                return result > 0 && Double.isInfinite(result);
            }
            else {
                return result < 0 && Double.isInfinite(result);
            }
        }
        else if (Double.isNaN(result) || Double.isInfinite(result)) {
            return false;
        }
        else if (Math.abs(result - expected) < MAX_DOUBLE_ERROR) {
            return true;
        }
        else {
            double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR));
            double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR));
            return result > min && result < max;
        }
    }

    static double relativeError(double expected, double result) {
        if (Double.isNaN(expected) || Double.isInfinite(expected) || Double.isNaN(result) || Double.isInfinite(result) || expected == 0)
            return 0;
        return Math.abs(result - expected) / Math.abs(expected);
    }

    static String formatResult(double res) {
        return String.format("%.10g", res);
    }

    static int verifyCase(int casenum, double expected, double received) {
        System.err.print("Example " + casenum + "... ");
        if (compareOutput(expected, received)) {
            System.err.print("PASSED");
            double rerr = relativeError(expected, received);
            if (rerr > 0) System.err.printf(" (relative error %g)", rerr);
            System.err.println();
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
                double price = 6800;
                double monthlyPayment = 100;
                int loanTerm = 68;
                double expected__ = 1.3322616182218813E-13;

                return verifyCase(casenum__, expected__, new AutoLoan().interestRate(price, monthlyPayment, loanTerm));
            }
            case 1: {
                double price = 2000;
                double monthlyPayment = 510;
                int loanTerm = 4;
                double expected__ = 9.56205462458368;

                return verifyCase(casenum__, expected__, new AutoLoan().interestRate(price, monthlyPayment, loanTerm));
            }
            case 2: {
                double price = 15000;
                double monthlyPayment = 364;
                int loanTerm = 48;
                double expected__ = 7.687856394581649;

                return verifyCase(casenum__, expected__, new AutoLoan().interestRate(price, monthlyPayment, loanTerm));
            }

            // custom cases

/*      case 3: {
            double price              = ;
			double monthlyPayment     = ;
			int loanTerm              = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new AutoLoan().interestRate(price, monthlyPayment, loanTerm));
		}*/
/*      case 4: {
            double price              = ;
			double monthlyPayment     = ;
			int loanTerm              = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new AutoLoan().interestRate(price, monthlyPayment, loanTerm));
		}*/
/*      case 5: {
            double price              = ;
			double monthlyPayment     = ;
			int loanTerm              = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new AutoLoan().interestRate(price, monthlyPayment, loanTerm));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
