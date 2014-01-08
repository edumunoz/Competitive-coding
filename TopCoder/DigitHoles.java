// Paste me into the FileEdit configuration dialog

public class DigitHoles {
   public int numHoles(int number) {
		int holeNo = 0;
		int digit;
		while (number > 0) {
		    digit = number % 10;
		    if (digit == 0 || digit == 4 || digit == 6 || digit == 9) {
		        holeNo++;
		    }
		    else if (digit == 8) {
		        holeNo += 2;
		    }
		    number /= 10;
		}
		return holeNo;
   }


// BEGIN CUT HERE
   public static void main(String[] args) {
		if (args.length == 0) {
			DigitHolesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				DigitHolesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class DigitHolesHarness {
	public static void run_test(int casenum) {
		if (casenum != -1) {
			if (runTestCase(casenum) == -1)
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for (int i=0;; ++i) {
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
	
	static boolean compareOutput(int expected, int result) { return expected == result; }
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
		switch(casenum__) {
		case 0: {
			int number                = 42;
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new DigitHoles().numHoles(number));
		}
		case 1: {
			int number                = 669;
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new DigitHoles().numHoles(number));
		}
		case 2: {
			int number                = 688;
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new DigitHoles().numHoles(number));
		}
		case 3: {
			int number                = 123;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new DigitHoles().numHoles(number));
		}
		case 4: {
			int number                = 456;
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new DigitHoles().numHoles(number));
		}
		case 5: {
			int number                = 789;
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new DigitHoles().numHoles(number));
		}

		// custom cases

/*      case 6: {
			int number                = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DigitHoles().numHoles(number));
		}*/
/*      case 7: {
			int number                = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DigitHoles().numHoles(number));
		}*/
/*      case 8: {
			int number                = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DigitHoles().numHoles(number));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
