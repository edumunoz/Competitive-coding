// Paste me into the FileEdit configuration dialog

public class Salary {
    public int howMuch(String[] arrival, String[] departure, int wage) {
        int normalSeconds = 0, nightSeconds = 0;

        for (int i = 0; i < arrival.length; i++) {
            String[] arrivalTime = arrival[i].split(":");
            String[] departureTime = departure[i].split(":");
            int arrivalHour, arrivalMin, arrivalSec, departureHour, departureMin, departureSec;
            arrivalHour = arrivalMin = arrivalSec = departureHour = departureMin = departureSec = 0;
            arrivalHour = Integer.parseInt(arrivalTime[0]);
            arrivalMin = Integer.parseInt(arrivalTime[1]);
            arrivalSec = Integer.parseInt(arrivalTime[2]);
            departureHour = Integer.parseInt(departureTime[0]);
            departureMin = Integer.parseInt(departureTime[1]);
            departureSec = Integer.parseInt(departureTime[2]);

            int arrivalTimeSec = arrivalHour * 3600 + arrivalMin * 60 + arrivalSec;
            int departureTimeSec = departureHour * 3600 + departureMin * 60 + departureSec;

            if (departureHour < 6) {
                nightSeconds += departureTimeSec - arrivalTimeSec;
            }

            if (departureHour > 6 && departureHour <= 18) {
                normalSeconds += -(arrivalHour < 6 ? 6 * 3600 : arrivalTimeSec) + departureTimeSec;
                if (arrivalHour < 6)
                    nightSeconds += 6 * 3600 - arrivalTimeSec;
            }

            if (departureHour > 18) {
                nightSeconds += -(arrivalHour >= 18 ? arrivalTimeSec : 18 * 3600) + departureTimeSec;
                if (arrivalHour < 18)
                    normalSeconds += -(arrivalHour < 6 ? 6 * 3600 : arrivalTimeSec) + 18 * 3600;
                if (arrivalHour < 6)
                    nightSeconds += 6 * 3600 - arrivalTimeSec;
            }
        }

        return (int) ((normalSeconds + 1.5 * nightSeconds) * wage / 3600.0);
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SalaryHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                SalaryHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SalaryHarness {
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
                String[] arrival = {"08:00:00", "13:00:00", "19:27:32"};
                String[] departure = {"12:00:00", "17:00:00", "20:48:10"};
                int wage = 1000;
                int expected__ = 10015;

                return verifyCase(casenum__, expected__, new Salary().howMuch(arrival, departure, wage));
            }
            case 1: {
                String[] arrival = {"01:05:47", "16:48:12"};
                String[] departure = {"09:27:30", "21:17:59"};
                int wage = 2000;
                int expected__ = 33920;

                return verifyCase(casenum__, expected__, new Salary().howMuch(arrival, departure, wage));
            }
            case 2: {
                String[] arrival = {"00:00:00"};
                String[] departure = {"23:59:59"};
                int wage = 10000;
                int expected__ = 299995;

                return verifyCase(casenum__, expected__, new Salary().howMuch(arrival, departure, wage));
            }
            case 3: {
                String[] arrival = {"10:00:00"};
                String[] departure = {"18:00:00"};
                int wage = 10000;
                int expected__ = 80000;

                return verifyCase(casenum__, expected__, new Salary().howMuch(arrival, departure, wage));
            }
            case 4: {
                String[] arrival = {"22:19:46"};
                String[] departure = {"23:12:46"};
                int wage = 5320;
                int expected__ = 7049;

                return verifyCase(casenum__, expected__, new Salary().howMuch(arrival, departure, wage));
            }

            // custom cases

/*      case 5: {
            String[] arrival          = ;
			String[] departure        = ;
			int wage                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Salary().howMuch(arrival, departure, wage));
		}*/
/*      case 6: {
            String[] arrival          = ;
			String[] departure        = ;
			int wage                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Salary().howMuch(arrival, departure, wage));
		}*/
/*      case 7: {
            String[] arrival          = ;
			String[] departure        = ;
			int wage                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Salary().howMuch(arrival, departure, wage));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
