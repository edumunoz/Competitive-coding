// Paste me into the FileEdit configuration dialog

public class BestApproximationDiv2 {
   public String findFraction(int maxDen, String number) {
       Double in = Double.parseDouble(number);
       
       int trailingZeros = number.length() - in.toString().length();
       
       int bb = 1;
       int aa = 0;
       int maxScore = -1;
       int currentScore = -1;
       
       for (int b = 1; b <= maxDen; ++b) {
           for (int a = 0; a <= b; ++a) {
               Double currentF = ((double) a) / b;
               currentScore = CalculateScore(number, currentF.toString());
               if (currentScore > maxScore) {
                   bb = b;
                   aa = a;
                   maxScore = currentScore;
               }
           }
       }
       
       return String.format("%d/%d has %d exact digits", aa, bb, maxScore+1);
		
   }

   public int CalculateScore(String s1, String s2) {
       int length1 = s1.length();
       int length2 = s2.length();
       if (length1 > length2) {
           StringBuffer zeros = new StringBuffer(length1 - length2);
           for (int i = 0; i < s1.length() - s2.length(); ++i) {
               zeros.append('0');
           }
           s2 += zeros.toString();
       }
       int score = 0;
       while (score+2 < s1.length() && score+2 < s2.length() && s1.charAt(score+2) == s2.charAt(score+2)) {
           score++;
       }
       return score;
   }

// BEGIN CUT HERE
   public static void main(String[] args) {
		if (args.length == 0) {
			BestApproximationDiv2Harness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				BestApproximationDiv2Harness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class BestApproximationDiv2Harness {
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
			int maxDen                = 42;
			String number             = "0.141592658";
			String expected__         = "1/7 has 3 exact digits";

			return verifyCase(casenum__, expected__, new BestApproximationDiv2().findFraction(maxDen, number));
		}
		case 1: {
			int maxDen                = 3;
			String number             = "0.1337";
			String expected__         = "0/1 has 1 exact digits";

			return verifyCase(casenum__, expected__, new BestApproximationDiv2().findFraction(maxDen, number));
		}
//		case 2: {
//			int maxDen                = 80000;
//			String number             = "0.1234567891011121314151617181920";
//			String expected__         = "10/81 has 8 exact digits";
//
//			return verifyCase(casenum__, expected__, new BestApproximationDiv2().findFraction(maxDen, number));
//		}
		case 3: {
			int maxDen                = 1000;
			String number             = "0.42";
			String expected__         = "3/7 has 3 exact digits";

			return verifyCase(casenum__, expected__, new BestApproximationDiv2().findFraction(maxDen, number));
		}
		case 4: {
			int maxDen                = 100;
			String number             = "0.420";
			String expected__         = "21/50 has 4 exact digits";

			return verifyCase(casenum__, expected__, new BestApproximationDiv2().findFraction(maxDen, number));
		}
		case 5: {
			int maxDen                = 115;
			String number             = "0.141592658";
			String expected__         = "16/113 has 7 exact digits";

			return verifyCase(casenum__, expected__, new BestApproximationDiv2().findFraction(maxDen, number));
		}

		// custom cases

/*      case 6: {
			int maxDen                = ;
			String number             = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new BestApproximationDiv2().findFraction(maxDen, number));
		}*/
/*      case 7: {
			int maxDen                = ;
			String number             = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new BestApproximationDiv2().findFraction(maxDen, number));
		}*/
/*      case 8: {
			int maxDen                = ;
			String number             = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new BestApproximationDiv2().findFraction(maxDen, number));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
