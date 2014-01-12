// Paste me into the FileEdit configuration dialog

import java.util.ArrayList;
import java.util.List;

import java.util.LinkedList;

public class MiddleCode {
    public String encode(String s) {
        StringBuffer sb = new StringBuffer();

        List<Character> chars = new LinkedList<Character>();

        for (char c : s.toCharArray()) {
            chars.add(c);
        }

        char current, mid1;
        while (!chars.isEmpty()) {
            if (chars.size() == 1)
                current = chars.remove(0);

            else if (chars.size() % 2 == 0) {
                // size is even
                mid1 = chars.get(chars.size() / 2 - 1);
                current = chars.get(chars.size() / 2);
                if (mid1 < current) {
                    chars.remove(chars.size() / 2 - 1);
                    current = mid1;
                }
                else {
                    chars.remove(chars.size() / 2);
                }
            }
            else {
                // size is odd
                current = chars.remove(chars.size() / 2);
            }
            sb.append(current);
        }

        return sb.toString();
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MiddleCodeHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                MiddleCodeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MiddleCodeHarness {
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

    static boolean compareOutput(String expected, String result) {
        return expected.equals(result);
    }

    static String formatResult(String res) {
        return String.format("\"%s\"", res);
    }

    static int verifyCase(int casenum, String expected, String received) {
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
                String s = "word";
                String expected__ = "ordw";

                return verifyCase(casenum__, expected__, new MiddleCode().encode(s));
            }
            case 1: {
                String s = "aaaaa";
                String expected__ = "aaaaa";

                return verifyCase(casenum__, expected__, new MiddleCode().encode(s));
            }
            case 2: {
                String s = "abacaba";
                String expected__ = "caabbaa";

                return verifyCase(casenum__, expected__, new MiddleCode().encode(s));
            }
            case 3: {
                String s = "shjegr";
                String expected__ = "ejghrs";

                return verifyCase(casenum__, expected__, new MiddleCode().encode(s));
            }
            case 4: {
                String s = "adafaaaadafawafwfasdaa";
                String expected__ = "afawadafawafaafsadadaa";

                return verifyCase(casenum__, expected__, new MiddleCode().encode(s));
            }

            // custom cases

/*      case 5: {
            String s                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new MiddleCode().encode(s));
		}*/
/*      case 6: {
            String s                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new MiddleCode().encode(s));
		}*/
/*      case 7: {
            String s                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new MiddleCode().encode(s));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
