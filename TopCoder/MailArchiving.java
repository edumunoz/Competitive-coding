// Paste me into the FileEdit configuration dialog

import java.util.Arrays;

public class MailArchiving {
    public int minSelections(String[] destFolders) {
        // dp[i][j] = min no selections for elements between i and j (inclusive)
        int dp[][] = new int[destFolders.length][destFolders.length];

        for (int i = 0; i < destFolders.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 1;
        }

        for (int length = 1; length < destFolders.length; length++)
            for (int i = 0; i + length < destFolders.length; i++)
                for (int k = i; k < length + i; k++) {
                    dp[i][i + length] = Math.min(dp[i][i + length],
                                            dp[i][k] + dp[k + 1][i + length] - (destFolders[i].equals(destFolders[i+length]) ? 1 : 0));
                }

        return dp[0][destFolders.length - 1];
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MailArchivingHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                MailArchivingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MailArchivingHarness {
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
                String[] destFolders = {"Deleted messages", "Saved messages", "Deleted messages"};
                int expected__ = 2;

                return verifyCase(casenum__, expected__, new MailArchiving().minSelections(destFolders));
            }
            case 1: {
                String[] destFolders = {"Folder A", "Folder B", "Folder C", "Folder D", "Folder E", "Folder F"};
                int expected__ = 6;

                return verifyCase(casenum__, expected__, new MailArchiving().minSelections(destFolders));
            }
            case 2: {
                String[] destFolders = {"FOLDER", "folder"};
                int expected__ = 2;

                return verifyCase(casenum__, expected__, new MailArchiving().minSelections(destFolders));
            }
            case 3: {
                String[] destFolders = {"a", "b", "a", "c", "a", "a", "b", "a", "c", "d", "a"};
                int expected__ = 6;

                return verifyCase(casenum__, expected__, new MailArchiving().minSelections(destFolders));
            }
            case 4: {
                String[] destFolders = {"a", "b", "b", "c", "d", "e", "d", "e", "d", "e",
                        "c", "c", "a", "a", "a", "f", "g", "g", "f", "a",
                        "h", "h", "i", "j", "i", "j", "a", "a", "k", "k",
                        "l", "m", "k", "l", "m", "a", "o", "o", "p", "a"};
                int expected__ = 20;

                return verifyCase(casenum__, expected__, new MailArchiving().minSelections(destFolders));
            }

            // custom cases

/*      case 5: {
            String[] destFolders      = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new MailArchiving().minSelections(destFolders));
		}*/
/*      case 6: {
            String[] destFolders      = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new MailArchiving().minSelections(destFolders));
		}*/
/*      case 7: {
            String[] destFolders      = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new MailArchiving().minSelections(destFolders));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
