// Paste me into the FileEdit configuration dialog

import java.util.LinkedList;
import java.util.Queue;

public class PowerOfThreeEasy {

    class Node {
        int x, y, steps;

        public Node(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }

    public String ableToGet(int x, int y) {

        Node n = new Node(0, 0, 0);

        Queue<Node> q = new LinkedList<Node>();
        q.add(n);

        while (!q.isEmpty()) {
            n = q.remove();

            if (n.x > x || n.y > y)
                continue;

            if (n.x == x && n.y == y)
                return "Possible";

            q.add(new Node(n.x + (int)Math.pow(3, n.steps), n.y, n.steps+1));
            q.add(new Node(n.x, n.y + (int)Math.pow(3, n.steps), n.steps+1));
        }

        return "Impossible";
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PowerOfThreeEasyHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                PowerOfThreeEasyHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PowerOfThreeEasyHarness {
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
                int x = 1;
                int y = 3;
                String expected__ = "Possible";

                return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
            }
            case 1: {
                int x = 1;
                int y = 1;
                String expected__ = "Impossible";

                return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
            }
            case 2: {
                int x = 3;
                int y = 0;
                String expected__ = "Impossible";

                return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
            }
            case 3: {
                int x = 1;
                int y = 9;
                String expected__ = "Impossible";

                return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
            }
            case 4: {
                int x = 3;
                int y = 10;
                String expected__ = "Possible";

                return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
            }
            case 5: {
                int x = 1093;
                int y = 2187;
                String expected__ = "Possible";

                return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
            }
            case 6: {
                int x = 0;
                int y = 0;
                String expected__ = "Possible";

                return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
            }

            // custom cases

/*      case 7: {
            int x                     = ;
			int y                     = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
		}*/
/*      case 8: {
            int x                     = ;
			int y                     = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
		}*/
/*      case 9: {
            int x                     = ;
			int y                     = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new PowerOfThreeEasy().ableToGet(x, y));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
