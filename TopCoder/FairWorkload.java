// Paste me into the FileEdit configuration dialog

import java.util.Arrays;
import java.util.Collections;

public class FairWorkload {

    // Binary search:

    /*
    Search space: maximum number of folders any worker needs to look through.
    We have a function f: f(folders) = workers. Given a maximum number of folders all workers need to look through
    return the minimum number of workers needed.

    The predicate p: Can we look through x folders with w workers or less?
    For the first x such that p(x) is true, will be answer.

    We can calculate f(x) as follows:
    - starting at cabinet 0, assign it to the first worker if it's not greater than x, if it is, it's not possible
    - if folders@cabinet(0) <= x, assign it to the first worker. If folders@cabinet(1) <= x-folders@cabinet(0)
    - then assign it to the first worker, other wise "start" a new worker
     */

    public int getMostWork(int[] folders, int workers) {


        int lo = 2, hi = 15000;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // calculate minimum number of workers needed so that maximum folders anyone looks through is mid
            int workersRequired = 1;
            int lookedFolders = 0;
            for (int cabinet = 0; cabinet < folders.length; cabinet++) {
                if (folders[cabinet] <= mid - lookedFolders)
                    lookedFolders += folders[cabinet];
                else if (folders[cabinet] > mid) {
                    // it's not possible to have mid as being the maximum no.
                    // of folders any worker will look through, so set it to workers+1
                    // so that we bump up lo
                    workersRequired = workers + 1;
                }
                else {
                workersRequired++;
                lookedFolders = folders[cabinet];
            }
        }

        if (workersRequired <= workers) {
            hi = mid;
        }
        else
            lo = mid + 1;
    }

    return lo;
}

    // Dynamic programming
/*
    public int getMostWork(int[] folders, int workers) {
        int DP[][] = new int[folders.length + 1][workers + 1];
        int prefixes[] = new int[folders.length + 1];


        for (int i = 1; i <= folders.length; i++) {
            prefixes[i] = prefixes[i - 1] + folders[i - 1];
        }

        for (int w = 1; w <= workers; w++)
            DP[1][w] = folders[0];

        for (int f = 1; f <= folders.length; f++)
            DP[f][1] = prefixes[f];

        for (int f = 2; f <= folders.length; f++) {
            for (int w = 2; w <= workers; w++) {
                DP[f][w] = Integer.MAX_VALUE;
                for (int i = 1; i < f; i++) {
                    DP[f][w] = Math.min(DP[f][w], Math.max(prefixes[f] - prefixes[i], DP[i][w - 1]));
                }
            }
        }
        return DP[folders.length][workers];
    }
*/


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FairWorkloadHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                FairWorkloadHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FairWorkloadHarness {
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
                int[] folders = {10, 20, 30, 40, 50, 60, 70, 80, 90};
                int workers = 3;
                int expected__ = 170;

                return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
            }
            case 1: {
                int[] folders = {10, 20, 30, 40, 50, 60, 70, 80, 90};
                int workers = 5;
                int expected__ = 110;

                return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
            }
            case 2: {
                int[] folders = {568, 712, 412, 231, 241, 393, 865, 287, 128, 457, 238, 98, 980, 23, 782};
                int workers = 4;
                int expected__ = 1785;

                return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
            }
            case 3: {
                int[] folders = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1000};
                int workers = 2;
                int expected__ = 1000;

                return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
            }
            case 4: {
                int[] folders = {50, 50, 50, 50, 50, 50, 50};
                int workers = 2;
                int expected__ = 200;

                return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
            }
            case 5: {
                int[] folders = {1, 1, 1, 1, 100};
                int workers = 5;
                int expected__ = 100;

                return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
            }
            case 6: {
                int[] folders = {950, 650, 250, 250, 350, 100, 650, 150, 150, 700};
                int workers = 6;
                int expected__ = 950;

                return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
            }

            // custom cases

/*      case 7: {
            int[] folders             = ;
			int workers               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
		}*/
/*      case 8: {
            int[] folders             = ;
			int workers               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
		}*/
/*      case 9: {
            int[] folders             = ;
			int workers               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FairWorkload().getMostWork(folders, workers));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
