// Paste me into the FileEdit configuration dialog

import java.util.*;

public class FoxAndWord {

    public void permutations(String s, List<String> l, int number) {
        if (s.length() - 1 == number)
            return;
        String w = s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
        l.add(w);
        permutations(w, l, number + 1);
    }

    public int howManyPairs(String[] words) {
        int result = 0;

        String s;
        for (int i = 0; i < words.length; i++) {
            s = words[i];
            List<String> perms = new ArrayList<String>();
            permutations(s, perms, 0);

            for (int j = i + 1; j < words.length; j++) {
                for (String w : perms) {
                    if (w.equals(words[j])) {
                        result++;
                        break;
                    }
                }
            }
        }

        return result;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxAndWordHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                FoxAndWordHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndWordHarness {
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
                String[] words = {"tokyo", "kyoto"};
                int expected__ = 1;

                return verifyCase(casenum__, expected__, new FoxAndWord().howManyPairs(words));
            }
            case 1: {
                String[] words = {"aaaaa", "bbbbb"};
                int expected__ = 0;

                return verifyCase(casenum__, expected__, new FoxAndWord().howManyPairs(words));
            }
            case 2: {
                String[] words = {"ababab", "bababa", "aaabbb"};
                int expected__ = 1;

                return verifyCase(casenum__, expected__, new FoxAndWord().howManyPairs(words));
            }
            case 3: {
                String[] words = {"eel", "ele", "lee"};
                int expected__ = 3;

                return verifyCase(casenum__, expected__, new FoxAndWord().howManyPairs(words));
            }
            case 4: {
                String[] words = {"aaa", "aab", "aba", "abb", "baa", "bab", "bba", "bbb"};
                int expected__ = 6;

                return verifyCase(casenum__, expected__, new FoxAndWord().howManyPairs(words));
            }
            case 5: {
                String[] words = {"top", "coder"};
                int expected__ = 0;

                return verifyCase(casenum__, expected__, new FoxAndWord().howManyPairs(words));
            }

            // custom cases

/*      case 6: {
            String[] words            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndWord().howManyPairs(words));
		}*/
/*      case 7: {
            String[] words            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndWord().howManyPairs(words));
		}*/
/*      case 8: {
            String[] words            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndWord().howManyPairs(words));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
