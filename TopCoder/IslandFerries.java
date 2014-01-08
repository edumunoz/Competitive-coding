// Paste me into the FileEdit configuration dialog

import org.omg.CORBA._IDLTypeStub;

import java.util.*;

public class IslandFerries {
    int noServices;
    int noIslands;
    boolean[][][][] visited;
    Node[][][][][] parent;
    boolean[][][] map;
    int[][] ticketCost;

    List<Integer> TicketFrom(int ticket, int from) {
        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < this.map[ticket][from].length; i++) {
            if (map[ticket][from][i])
                result.add(i);
        }

        return result;
    }

    public int[] costs(String[] legs, String[] prices) {
        noServices = legs.length;
        noIslands = prices.length;

       // parent = new Node[noIslands][noServices + 1][noServices + 1][noServices + 1][9999];
        int[] result = new int[noIslands - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = -1;
        }

        this.map = new boolean[noServices + 1][noIslands][noIslands];
        this.ticketCost = new int[noIslands][noServices];

        for (int r = 0; r < legs.length; r++) {
            String[] routes = legs[r].split(" ");
            int from, to, cost;

            for (String route : routes) {
                from = Integer.parseInt(route.split("-")[0]);
                to = Integer.parseInt(route.split("-")[1]);
                map[r][from][to] = true;

                for (int island = 0; island < prices.length; island++) {
                    cost = Integer.parseInt(prices[island].split(" ")[r]);
                    this.ticketCost[island][r] = cost;
                }
            }
        }

        visited = new boolean[noIslands][noServices + 1][noServices + 1][noServices + 1];

        Node n = new Node(0, noServices, noServices, noServices, 0);
        Queue<Node> q = new PriorityQueue<Node>(10, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        q.add(n);

        while (!q.isEmpty()) {
            n = q.remove();

            if (visited[n.island][n.ticket1][n.ticket2][n.ticket3])
                continue;


            visited[n.island][n.ticket1][n.ticket2][n.ticket3] = true;

            if (n.island != 0 && result[n.island - 1] == -1)
                result[n.island - 1] = n.cost;

            addToQueue(q, n.island, n.ticket1, n.ticket2, n.ticket3, n.cost);
            addToQueue(q, n.island, n.ticket2, n.ticket1, n.ticket3, n.cost);
            addToQueue(q, n.island, n.ticket3, n.ticket1, n.ticket2, n.cost);
        }
        return result;
    }

    void addToQueue(Queue<Node> q, int from, int ticketUsed, int ticketUnused1, int ticketUnused2, int prevCost) {

        int[] tickets = new int[3];
        Node n = null;
        // using a ticket
        if (ticketUsed != noServices) {
            for (int to : TicketFrom(ticketUsed, from)) {
                tickets[0] = noServices;
                tickets[1] = ticketUnused1;
                tickets[2] = ticketUnused2;
                Arrays.sort(tickets);
                n = new Node(to, tickets[0], tickets[1], tickets[2], prevCost);
                q.add(n);
                //parent[n.island][n.ticket1][n.ticket2][n.ticket3][n.cost] = new Node(from, ticketUsed, tickets[0], tickets[1], prevCost);
            }
        }

        // buying a ticket
        else {
            for (int ticket = 0; ticket < noServices; ticket++) {
                if (ticket != ticketUnused1 && ticket != ticketUnused2) {
                    tickets[0] = ticket;
                    tickets[1] = ticketUnused1;
                    tickets[2] = ticketUnused2;
                    Arrays.sort(tickets);
                    n = new Node(from, tickets[0], tickets[1], tickets[2], prevCost + this.ticketCost[from][ticket]);
                    q.add(n);
                    //parent[n.island][n.ticket1][n.ticket2][n.ticket3][n.cost] = new Node(from, ticketUsed, ticketUnused1, ticketUnused2, prevCost);
                }
            }
        }
    }

    class Node {
        int island, ticket1, ticket2, ticket3, cost;

        public Node(int island, int ticket1, int ticket2, int ticket3, int cost) {
            this.island = island;
            this.ticket1 = ticket1;
            this.ticket2 = ticket2;
            this.ticket3 = ticket3;
            this.cost = cost;
        }
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            IslandFerriesHarness.run_test(-1);
        } else {
            for (int i = 0; i < args.length; ++i)
                IslandFerriesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class IslandFerriesHarness {
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

    static boolean compareOutput(int[] expected, int[] result) {
        if (expected.length != result.length) return false;
        for (int i = 0; i < expected.length; ++i) if (expected[i] != result[i]) return false;
        return true;
    }

    static String formatResult(int[] res) {
        String ret = "";
        ret += "{";
        for (int i = 0; i < res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, int[] expected, int[] received) {
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
                String[] legs = {"0-1 0-3", "0-2"};
                String[] prices = {"5 7", "1000 1000", "1000 1000", "1000 1000"};
                int[] expected__ = {5, 7, 5};

                return verifyCase(casenum__, expected__, new IslandFerries().costs(legs, prices));
            }
            case 1: {
                String[] legs = {"0-1 1-2 2-3", "0-1 2-3"};
                String[] prices = {"1 10", "20 25", "50 50", "1000 1000", "1000 1000"};
                int[] expected__ = {1, 11, 31, -1};

                return verifyCase(casenum__, expected__, new IslandFerries().costs(legs, prices));
            }
            case 2: {
                String[] legs = {"0-1", "1-0", "0-2", "2-3"};
                String[] prices = {"1 1 1000 1000", "1000 1000 10 100", "1000 1000 1000 1000", "1000 1000 1000 1000"};
                int[] expected__ = {1, 12, 112};

                return verifyCase(casenum__, expected__, new IslandFerries().costs(legs, prices));
            }
            case 3: {
                String[] legs = {"1-0"};
                String[] prices = {"793", "350"};
                int[] expected__ = {-1};

                return verifyCase(casenum__, expected__, new IslandFerries().costs(legs, prices));
            }
            case 4: {
                String[] legs = {"8-18 4-11 15-5 7-12 11-8 0-15 15-2 3-11 4-18 2-3",
                        "16-2 18-3 15-18 11-19 18-2 18-7 19-17 3-15 12-19",
                        "2-17 0-12 1-2 14-12 6-2 4-2 11-5 4-11 11-6 17-16",
                        "0-18 13-18 16-0 3-7 14-12 3-1 19-18 3-19 10-3 8-15",
                        "18-19 3-16 13-6 0-19 12-14 5-17 1-12 7-13 9-14 1-2",
                        "14-5 17-9 2-10 16-13 11-15 10-17 14-10 0-14 2-13",
                        "4-5 0-17 6-9 17-7 12-6 5-6 6-18 10-18 0-2 13-0 8-4",
                        "3-12 4-11 10-17 13-12 3-0 3-7 13-0 9-15 10-9 0-10"};
                String[] prices = {"592 219 88 529 324 86 503 610",
                        "2 94 8 600 34 95 6 494",
                        "638 301 10 246 290 97 85 74",
                        "118 8 939 393 450 79 317 99",
                        "99 806 698 740 2 26 525 818",
                        "95 9 615 972 23 23 5 666",
                        "6 448 440 710 83 4 419 496",
                        "4 47 182 4 185 70 718 770",
                        "3 321 6 855 968 65 10 6",
                        "173 224 300 3 79 2 707 49",
                        "21 10 7 10 4 9 5 8",
                        "6 600 4 724 7 1 960 247",
                        "83 16 901 50 437 780 658 2",
                        "763 923 125 576 45 423 3 1",
                        "7 324 391 898 8 59 281 973",
                        "9 44 49 364 78 744 43 5",
                        "10 62 75 418 216 90 32 919",
                        "764 534 778 605 80 647 821 74",
                        "65 449 102 867 421 94 6 7",
                        "67 155 362 789 189 316 107 595"};
                int[] expected__ = {170, 160, 155, 173, 145, 150, 158, 168, 153, 145, 162, 88, 162, 86, 163, 159, 153, 150, 104};

                return verifyCase(casenum__, expected__, new IslandFerries().costs(legs, prices));
            }

            // custom cases

/*      case 5: {
            String[] legs             = ;
			String[] prices           = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new IslandFerries().costs(legs, prices));
		}*/
/*      case 6: {
            String[] legs             = ;
			String[] prices           = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new IslandFerries().costs(legs, prices));
		}*/
/*      case 7: {
            String[] legs             = ;
			String[] prices           = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new IslandFerries().costs(legs, prices));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
