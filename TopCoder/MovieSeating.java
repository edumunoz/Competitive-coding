// Paste me into the FileEdit configuration dialog

public class MovieSeating {
    public long getSeatings(int numFriends, String[] hall) {
        long result = 0;
        int empties;
        
        // iterate rows
        for (int i = 0; i < hall.length; i++) {
            empties = getNumberOccurrences(hall[i].toCharArray(), '.');
            result += getKPermutationOfN(numFriends, empties);
        }
        
        if (numFriends == 1) return result;
        
        char[] temp = new char[hall.length];
        // iterate columns
        for (int i = 0; i < hall[0].length(); i++) {
            for (int j = 0; j < hall.length; j++) {
                temp[j] = hall[j].charAt(i);
            }
            empties = getNumberOccurrences(temp, '.');
            result += getKPermutationOfN(numFriends, empties);
        }
        
    return result;
   }

   private static long getKPermutationOfN(int k, int n) {
       long result = 1;
       int soFar = n;
       
       while (soFar > n - k) {
           result *= soFar;
           soFar--;
       }
       
       return result;       
   }
   
   private static int getNumberOccurrences(char[] s, char c) {
       int result = 0;
       for (int i = 0; i < s.length; i++) {
           if (s[i] == c) {
               result++;
           }
       }
       
       return result;
   }


// BEGIN CUT HERE
   public static void main(String[] args) {
        if (args.length == 0) {
            MovieSeatingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MovieSeatingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MovieSeatingHarness {
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
    
    static boolean compareOutput(long expected, long result) { return expected == result; }
    static String formatResult(long res) {
        return String.format("%d", res);
    }
    
    static int verifyCase(int casenum, long expected, long received) { 
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
            int numFriends            = 2;
            String[] hall             = { ".#..",
  ".##.",
  "...." };
            long expected__           = 34;

            return verifyCase(casenum__, expected__, new MovieSeating().getSeatings(numFriends, hall));
        }
        case 1: {
            int numFriends            = 2;
            String[] hall             = { "..#",
  ".##",
  "..." };
            long expected__           = 16;

            return verifyCase(casenum__, expected__, new MovieSeating().getSeatings(numFriends, hall));
        }
        case 2: {
            int numFriends            = 5;
            String[] hall             = { "..####..", 
  ".###.##.",
  ".######.",
  "#.#.#.#." };
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new MovieSeating().getSeatings(numFriends, hall));
        }
        case 3: {
            int numFriends            = 8;
            String[] hall             = { "........" };
            long expected__           = 40320;

            return verifyCase(casenum__, expected__, new MovieSeating().getSeatings(numFriends, hall));
        }

        // custom cases

      case 4: {
            int numFriends            = 1;
            String[] hall             = {"..####..", ".###.##.", ".######.", "#.#.#.#."};
            long expected__           = 13;

            return verifyCase(casenum__, expected__, new MovieSeating().getSeatings(numFriends, hall));
        }
      case 5: {
            int numFriends            = 6;
            String[] hall             = {"#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", ".", ".", "#", "."};
            long expected__           = 797448960;

            return verifyCase(casenum__, expected__, new MovieSeating().getSeatings(numFriends, hall));
        }
/*      case 6: {
            int numFriends            = ;
            String[] hall             = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new MovieSeating().getSeatings(numFriends, hall));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
