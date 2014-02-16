// Paste me into the FileEdit configuration dialog

import java.util.*;

public class PermissionTree {
    class Node {
        int id;
        int parentId;

        Node(int id, int parent) {
            this.id = id;
            this.parentId = parent;
        }
    }

    public int[] findHome(String[] folders, String[] users) {
        Map<Integer, Node> t = new HashMap<Integer, Node>();
        Map<String, List<Node>> acl = new HashMap<String, List<Node>>();

        Node root = new Node(0, -1);
        t.put(0, root);
        String admin = folders[0].split(" ")[1];
        acl.put(admin, new ArrayList<Node>());
        acl.get(admin).add(root);

        for (int i = 1; i < folders.length; i++) {
            int parentId = Integer.parseInt(folders[i].split(" ")[0]);
            String[] u = folders[i].split(" ")[1].split(",");
            Node n = new Node(i, parentId);
            t.put(i, n);

            for (String s : u) {
                if (!acl.containsKey(s))
                    acl.put(s, new ArrayList<Node>());
                acl.get(s).add(n);
            }
        }

        int[] result = new int[users.length];
        for (int i = 0; i < users.length; i++) {
            String u = users[i];
            Map<Node, Integer> visited = new HashMap<Node, Integer>();
            int lcaId = -1;
            if (acl.containsKey(u)) {
                for (Node n : acl.get(u)) {
                    while (n != null) {
                        if (!visited.containsKey(n))
                            visited.put(n, 0);
                        visited.put(n, visited.get(n)+1);
                        if (visited.get(n) == acl.get(u).size()) {
                            lcaId = n.id;
                            break;
                        }
                        n = t.get(n.parentId);
                    }
                }
            }
            result[i] = lcaId;
        }
        return result;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PermissionTreeHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                PermissionTreeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PermissionTreeHarness {
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
                String[] folders = {"0 Admin", "0 Joe,Phil", "0 Joe"};
                String[] users = {"Admin", "Joe", "Phil"};
                int[] expected__ = {0, 0, 1};

                return verifyCase(casenum__, expected__, new PermissionTree().findHome(folders, users));
            }
            case 1: {
                String[] folders = {"0 Admin"};
                String[] users = {"Peter", "Paul", "Mary"};
                int[] expected__ = {-1, -1, -1};

                return verifyCase(casenum__, expected__, new PermissionTree().findHome(folders, users));
            }
            case 2: {
                String[] folders = {"0 Admin", "2 John", "0 Peter,John", "0 Tim", "1 John"};
                String[] users = {"John"};
                int[] expected__ = {2};

                return verifyCase(casenum__, expected__, new PermissionTree().findHome(folders, users));
            }
            case 3: {
                String[] folders = {"0 Admin",
                        "0 Jeff", "1 Mark,Tim", "1 Tim,Jeff",
                        "0 Dan", "4 Ed", "4 Tom", "4 Kyle,Ed",
                        "0 Ben", "8 Rich", "8 Sam", "8 Tim"};
                String[] users = {"Jeff", "Ed", "Tim", "Steve"};
                int[] expected__ = {1, 4, 0, -1};

                return verifyCase(casenum__, expected__, new PermissionTree().findHome(folders, users));
            }
            case 4: {
                String[] folders = {"0 Admin", "0 Bob,Joe,Bob", "0 Joe"};
                String[] users = {"Joe", "Bob"};
                int[] expected__ = {0, 1};

                return verifyCase(casenum__, expected__, new PermissionTree().findHome(folders, users));
            }

            // custom cases

/*      case 5: {
            String[] folders          = ;
			String[] users            = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new PermissionTree().findHome(folders, users));
		}*/
/*      case 6: {
            String[] folders          = ;
			String[] users            = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new PermissionTree().findHome(folders, users));
		}*/
/*      case 7: {
            String[] folders          = ;
			String[] users            = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new PermissionTree().findHome(folders, users));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
