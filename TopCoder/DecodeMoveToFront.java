// Paste me into the FileEdit configuration dialog

public class DecodeMoveToFront {
   public String findKey(String[] plaintexts, String[] ciphertexts) {
		return null;
   }


// BEGIN CUT HERE
   public static void main(String[] args) {
		if (args.length == 0) {
			DecodeMoveToFrontHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				DecodeMoveToFrontHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class DecodeMoveToFrontHarness {
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
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
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
			String[] plaintexts       = {"TPCDR"};
			String[] ciphertexts      = {"GKYYL"};
			String expected__         = "------T-R-P------------DC--";

			return verifyCase(casenum__, expected__, new DecodeMoveToFront().findKey(plaintexts, ciphertexts));
		}
		case 1: {
			String[] plaintexts       = {"A","B"};
			String[] ciphertexts      = {"X","X"};
			String expected__         = "ERROR";

			return verifyCase(casenum__, expected__, new DecodeMoveToFront().findKey(plaintexts, ciphertexts));
		}
		case 2: {
			String[] plaintexts       = {"HELLO"};
			String[] ciphertexts      = {"HOWDY"};
			String expected__         = "ERROR";

			return verifyCase(casenum__, expected__, new DecodeMoveToFront().findKey(plaintexts, ciphertexts));
		}
		case 3: {
			String[] plaintexts       = {"FOUR SCORE AND","SEVEN YEARS AGO","OUR FOREFATHERS"};
			String[] ciphertexts      = {"ABCDEFGFEHFIJK","FHLBKIMDLKHFDNK","BCDEEEDHDIOPEFJ"};
			String expected__         = "FOUR SCEANDVYGTH-----------";

			return verifyCase(casenum__, expected__, new DecodeMoveToFront().findKey(plaintexts, ciphertexts));
		}
		case 4: {
			String[] plaintexts       = {"LIZARD","JACKAL","HOWLER MONKEY","BLOWFISH","LYNX","GIRAFFE","VULTURE","QUAIL"};
			String[] ciphertexts      = {"LG GLM","ZEQWCO","GFNMFLGRGLVGS","UMGOTJML","LQJY","OGKGSAG","WNNFCMG","XNFHN"};
			String expected__         = " ETAOIHNSRDLUWGCYMFPBKVQXJZ";

			return verifyCase(casenum__, expected__, new DecodeMoveToFront().findKey(plaintexts, ciphertexts));
		}
		case 5: {
			String[] plaintexts       = {"HI"};
			String[] ciphertexts      = {"AA"};
			String expected__         = "ERROR";

			return verifyCase(casenum__, expected__, new DecodeMoveToFront().findKey(plaintexts, ciphertexts));
		}

		// custom cases

/*      case 6: {
			String[] plaintexts       = ;
			String[] ciphertexts      = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new DecodeMoveToFront().findKey(plaintexts, ciphertexts));
		}*/
/*      case 7: {
			String[] plaintexts       = ;
			String[] ciphertexts      = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new DecodeMoveToFront().findKey(plaintexts, ciphertexts));
		}*/
/*      case 8: {
			String[] plaintexts       = ;
			String[] ciphertexts      = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new DecodeMoveToFront().findKey(plaintexts, ciphertexts));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
