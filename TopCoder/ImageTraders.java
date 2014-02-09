// Paste me into the FileEdit configuration dialog

import java.util.Arrays;

public class ImageTraders {
    int[][] price;

    private static boolean isSet(int n, int idx) {
        return (n >> idx & 1) == 1;
    }

    private static int set(int n, int idx) {
        return n | 1 << idx;
    }

    private static int unset(int n, int idx) {
        int mask = ~set(0, idx);
        return n & mask;
    }

    int cache[][][] = new int[10][15][1<<15];

    public int maxOwners(String[] price) {
        this.price = new int[price.length][price.length];
        for (int i = 0; i < price.length; i++) {
            for (int j = 0; j < price[0].length(); j++) {
                this.price[i][j] = price[i].charAt(j) - '0';
            }
        }
        int buyers = unset(0x00007fff, 0);
        return longest(0, 0, buyers);
    }

    private int longest(int p, int owner, int buyers) {
        if (this.cache[p][owner][buyers] != 0)
            return cache[p][owner][buyers];

        boolean end = true;
        for (int i = 0; end && i < price.length; i++) {
            end &= !isSet(buyers, i);
        }
        if (end)
            return 1;

        int ret = 1;
        for (int i = 1; i < price.length; i++) {
            if (i == owner)
                continue;

            if (isSet(buyers,i) && this.price[owner][i] >= p) {
                buyers = unset(buyers,i);
                ret = Math.max(ret, 1 + longest(this.price[owner][i], i, buyers));
                buyers = set(buyers, i);
            }
        }
        cache[p][owner][buyers] = ret;
        return ret;
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ImageTradersHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                ImageTradersHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ImageTradersHarness {
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
                String[] price = {
                        "01",
                        "10"
                };
                int expected__ = 2;

                return verifyCase(casenum__, expected__, new ImageTraders().maxOwners(price));
            }
            case 1: {
                String[] price = {
                        "022",
                        "101",
                        "110"
                };
                int expected__ = 2;

                return verifyCase(casenum__, expected__, new ImageTraders().maxOwners(price));
            }
            case 2: {
                String[] price = {
                        "01231",
                        "00231",
                        "00031",
                        "00002",
                        "00000"
                };
                int expected__ = 4;

                return verifyCase(casenum__, expected__, new ImageTraders().maxOwners(price));
            }
            case 3: {
                String[] price = {
                        "15555",
                        "11111",
                        "15111",
                        "11111",
                        "11111"
                };
                int expected__ = 3;

                return verifyCase(casenum__, expected__, new ImageTraders().maxOwners(price));
            }
            case 4: {
                String[] price = {
                        "0100000000",
                        "0020000000",
                        "0003000000",
                        "0000400000",
                        "0000050000",
                        "0000006000",
                        "0000000700",
                        "0000000080",
                        "0000000009",
                        "1111111111"
                };
                int expected__ = 10;

                return verifyCase(casenum__, expected__, new ImageTraders().maxOwners(price));
            }

            // custom cases

/*      case 5: {
            String[] price            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ImageTraders().maxOwners(price));
		}*/
/*      case 6: {
            String[] price            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ImageTraders().maxOwners(price));
		}*/
/*      case 7: {
            String[] price            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ImageTraders().maxOwners(price));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
