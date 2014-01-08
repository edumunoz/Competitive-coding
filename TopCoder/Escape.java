// Paste me into the FileEdit configuration dialog

import java.util.*;

public class Escape {
    // normal = 0
    // harmful = 1
    // deadly = 2
    int harm = 1, dead = 2;
    int[][] map;

    public int lowest(String[] harmful, String[] deadly) {
        int result = 0;
        map = new int[501][501];
        int x1, y1, x2, y2;
        for (String h : harmful) {
            String[] coordinates = h.split(" ");
            x1 = Integer.parseInt(coordinates[0]);
            y1 = Integer.parseInt(coordinates[1]);
            x2 = Integer.parseInt(coordinates[2]);
            y2 = Integer.parseInt(coordinates[3]);

            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    map[x][y] = harm;
                }
            }
        }

        for (String d : deadly) {
            String[] coordinates = d.split(" ");
            x1 = Integer.parseInt(coordinates[0]);
            y1 = Integer.parseInt(coordinates[1]);
            x2 = Integer.parseInt(coordinates[2]);
            y2 = Integer.parseInt(coordinates[3]);

            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    map[x][y] = dead;
                }
            }
        }


        return bfs(0, 0);
    }

    class Triple {
        int x, y, z;

        public Triple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        boolean isDead() {
            return map[this.x][this.y] == dead;
        }
    }

    int bfs(int x, int y) {
        int cost = -1;
        Triple current;
        if (map[x][y] == dead) {
            // don't go further
        }

        Queue<Triple> q = new PriorityQueue<Triple>(100, new Comparator<Triple>() {
            @Override
            public int compare(Triple o1, Triple o2) {
                return o1.z - o2.z;
            }
        });

        q.add(new Triple(x, y, 0));
        map[x][y] = dead;


        while (!q.isEmpty()) {
            current = q.remove();

            if (current.x == 500 && current.y == 500) {
                cost = current.z;
                break;
            }

            addToQueue(q, current.x, current.y + 1, current.z);
            addToQueue(q, current.x + 1, current.y, current.z);
            addToQueue(q, current.x, current.y - 1, current.z);
            addToQueue(q, current.x - 1, current.y, current.z);
        }

        return cost;
    }

    void addToQueue(Queue<Triple> q, int x, int y, int prev_z) {
        if (x < 0 || x > 500 || y < 0 || y > 500 || map[x][y] == dead)
            return;
        else {
            q.add(new Triple(x, y, prev_z + map[x][y]));
            map[x][y] = dead;
        }
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EscapeHarness.run_test(-1);
        } else {
            for (int i = 0; i < args.length; ++i)
                EscapeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EscapeHarness {
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
        } else if (correct < total) {
            System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
        } else {
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
        } else {
            System.err.println("FAILED");
            System.err.println("    Expected: " + formatResult(expected));
            System.err.println("    Received: " + formatResult(received));
            return 0;
        }
    }

    static int runTestCase(int casenum__) {
        switch (casenum__) {
            case 0: {
                String[] harmful = {};
                String[] deadly = {};
                int expected__ = 0;

                return verifyCase(casenum__, expected__, new Escape().lowest(harmful, deadly));
            }
            case 1: {
                String[] harmful = {"500 0 0 500"};
                String[] deadly = {"0 0 0 0"};
                int expected__ = 1000;

                return verifyCase(casenum__, expected__, new Escape().lowest(harmful, deadly));
            }
            case 2: {
                String[] harmful = {"0 0 250 250", "250 250 500 500"};
                String[] deadly = {"0 251 249 500", "251 0 500 249"};
                int expected__ = 1000;

                return verifyCase(casenum__, expected__, new Escape().lowest(harmful, deadly));
            }
            case 3: {
                String[] harmful = {"0 0 250 250", "250 250 500 500"};
                String[] deadly = {"0 250 250 500", "250 0 500 250"};
                int expected__ = -1;

                return verifyCase(casenum__, expected__, new Escape().lowest(harmful, deadly));
            }
            case 4: {
                String[] harmful = {"468 209 456 32",
                        "71 260 306 427",
                        "420 90 424 492",
                        "374 253 54 253",
                        "319 334 152 431",
                        "38 93 204 84",
                        "246 0 434 263",
                        "12 18 118 461",
                        "215 462 44 317",
                        "447 214 28 475",
                        "3 89 38 125",
                        "157 108 138 264",
                        "363 17 333 387",
                        "457 362 396 324",
                        "95 27 374 175",
                        "381 196 265 302",
                        "105 255 253 134",
                        "0 308 453 55",
                        "169 28 313 498",
                        "103 247 165 376",
                        "264 287 363 407",
                        "185 255 110 415",
                        "475 126 293 112",
                        "285 200 66 484",
                        "60 178 461 301",
                        "347 352 470 479",
                        "433 130 383 370",
                        "405 378 117 377",
                        "403 324 369 133",
                        "12 63 174 309",
                        "181 0 356 56",
                        "473 380 315 378"};
                String[] deadly = {"250 384 355 234",
                        "28 155 470 4",
                        "333 405 12 456",
                        "329 221 239 215",
                        "334 20 429 338",
                        "85 42 188 388",
                        "219 187 12 111",
                        "467 453 358 133",
                        "472 172 257 288",
                        "412 246 431 86",
                        "335 22 448 47",
                        "150 14 149 11",
                        "224 136 466 328",
                        "369 209 184 262",
                        "274 488 425 195",
                        "55 82 279 253",
                        "153 201 65 228",
                        "208 230 132 223",
                        "369 305 397 267",
                        "200 145 98 198",
                        "422 67 252 479",
                        "231 252 401 190",
                        "312 20 0 350",
                        "406 72 207 294",
                        "488 329 338 326",
                        "117 264 497 447",
                        "491 341 139 438",
                        "40 413 329 290",
                        "148 245 53 386",
                        "147 70 186 131",
                        "300 407 71 183",
                        "300 186 251 198",
                        "178 67 487 77",
                        "98 158 55 433",
                        "167 231 253 90",
                        "268 406 81 271",
                        "312 161 387 153",
                        "33 442 25 412",
                        "56 69 177 428",
                        "5 92 61 247"};
                int expected__ = 254;

                return verifyCase(casenum__, expected__, new Escape().lowest(harmful, deadly));
            }

            // custom cases

/*      case 5: {
            String[] harmful          = ;
			String[] deadly           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Escape().lowest(harmful, deadly));
		}*/
/*      case 6: {
            String[] harmful          = ;
			String[] deadly           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Escape().lowest(harmful, deadly));
		}*/
/*      case 7: {
            String[] harmful          = ;
			String[] deadly           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Escape().lowest(harmful, deadly));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
