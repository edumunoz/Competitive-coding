// Paste me into the FileEdit configuration dialog

public class TreeTraversals {
   public int width(int[] inOrder, int[] breadthFirst) {
		return 0;
   }


// BEGIN CUT HERE
   public static void main(String[] args) {
		if (args.length == 0) {
			TreeTraversalsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TreeTraversalsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TreeTraversalsHarness {
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
			int[] inOrder             = { 3,2,1,5,6,4,7 };
			int[] breadthFirst        = { 1,2,4,3,5,7,6 };
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new TreeTraversals().width(inOrder, breadthFirst));
		}
		case 1: {
			int[] inOrder             = { 1,2,3 };
			int[] breadthFirst        = { 1,2,3 };
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new TreeTraversals().width(inOrder, breadthFirst));
		}
		case 2: {
			int[] inOrder             = { 1,2,3,4,5 };
			int[] breadthFirst        = { 5,3,1,2,4 };
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new TreeTraversals().width(inOrder, breadthFirst));
		}

		// custom cases

/*      case 3: {
			int[] inOrder             = ;
			int[] breadthFirst        = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TreeTraversals().width(inOrder, breadthFirst));
		}*/
/*      case 4: {
			int[] inOrder             = ;
			int[] breadthFirst        = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TreeTraversals().width(inOrder, breadthFirst));
		}*/
/*      case 5: {
			int[] inOrder             = ;
			int[] breadthFirst        = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TreeTraversals().width(inOrder, breadthFirst));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
