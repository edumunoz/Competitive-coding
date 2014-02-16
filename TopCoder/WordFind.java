// Paste me into the FileEdit configuration dialog

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Trie {
    Trie[] edges;
    boolean isWord;
    int data;

    Trie() {
        this.edges = new Trie[26];
    }

    public boolean contains(String word) {
        if (word.isEmpty())
            return this.isWord;
        else {
            char index = word.charAt(0);
            if (this.edges[index - 'A'] == null)
                return false;
            else
                return edges[index - 'A'].contains(word.substring(1));
        }
    }

    public void add(String word) {
        if (word.isEmpty()) {
            this.isWord = true;
            return;
        }
        else {
            char index = word.charAt(0);

            if (this.edges[index - 'A'] == null)
                this.edges[index - 'A'] = new Trie();

            this.edges[index - 'A'].add(word.substring(1));
        }
    }

    public boolean isProperPrefix(String prefix) {
        if (prefix.isEmpty()) {
            return true;
        }
        else {
            char index = prefix.charAt(0);
            return this.edges[index - 'A'] != null && this.edges[index - 'A'].isProperPrefix(prefix.substring(1));
        }
    }
}

class Pair {
    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class WordFind {
    public String[] findWords(String[] grid, String[] wordList) {
        HashMap<String, Pair> indices = new HashMap<String, Pair>();

        // Create and populate trie
        Trie t = new Trie();
        for (int i = 0; i < wordList.length; i++) {
            String w = wordList[i];

            if (!(w.length() > grid.length) || !(w.length() > grid[0].length()))
                t.add(w);
        }

        // Search in the trie for words from the grid
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length(); col++) {
                if (!t.isProperPrefix(grid[row].charAt(col) + "")) {
                    continue;
                }
                List<String> foundWords = new ArrayList<String>();
                StringBuilder wordSoFar = new StringBuilder().append(grid[row].charAt(col));
                if (t.contains(wordSoFar.toString()))
                    foundWords.add(wordSoFar.toString());


                // go right
                for (int colAdvance = col + 1; colAdvance < grid[0].length(); colAdvance++) {
                    wordSoFar.append(grid[row].charAt(colAdvance));
                    if (!t.isProperPrefix(wordSoFar.toString()))
                        break;
                    if (t.contains(wordSoFar.toString())) {
                        foundWords.add(wordSoFar.toString());
                    }
                }

                // go down
                wordSoFar.setLength(1);
                for (int rowAdvance = row + 1; rowAdvance < grid.length; rowAdvance++) {
                    wordSoFar.append(grid[rowAdvance].charAt(col));
                    if (!t.isProperPrefix(wordSoFar.toString()))
                        break;
                    if (t.contains(wordSoFar.toString())) {
                        foundWords.add(wordSoFar.toString());
                    }
                }

                // go diagonal
                wordSoFar.setLength(1);
                for (int i = 1; col + i < grid[0].length() && row + i < grid.length; i++) {
                    wordSoFar.append(grid[row + i].charAt(col + i));
                    if (!t.isProperPrefix(wordSoFar.toString()))
                        break;
                    if (t.contains(wordSoFar.toString())) {
                        foundWords.add(wordSoFar.toString());
                    }
                }

                if (!foundWords.isEmpty()) {
                    for (String word : foundWords) {
                        // find foundWord index in wordList
                        if (word.equals("TNE")) {
                            int a = 3+3;
                        }
                        Pair p = indices.get(word);
                        if (p != null) {
                            if (row < p.x)
                                indices.put(word, new Pair(row, col));
                            else if (row == p.x) {
                                if (col < p.y)
                                    indices.put(word, new Pair(row, col));
                            }
                        }
                        else
                            indices.put(word, new Pair(row, col));
                    }
                }
            }
        }
        String[] result = new String[wordList.length];
        for (int i = 0; i < wordList.length; i++) {
            Pair idxs = indices.get(wordList[i]);
            result[i] = idxs == null ? "" : idxs.x + " " + idxs.y;
        }
        return result;
    }


    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            WordFindHarness.run_test(-1);
        }
        else {
            for (int i = 0; i < args.length; ++i)
                WordFindHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class WordFindHarness {
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

    static boolean compareOutput(String[] expected, String[] result) {
        if (expected.length != result.length) return false;
        for (int i = 0; i < expected.length; ++i) if (!expected[i].equals(result[i])) return false;
        return true;
    }

    static String formatResult(String[] res) {
        String ret = "";
        ret += "{";
        for (int i = 0; i < res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" \"%s\"", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, String[] expected, String[] received) {
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
                String[] grid = {"TEST",
                        "GOAT",
                        "BOAT"};
                String[] wordList = {"GOAT", "BOAT", "TEST"};
                String[] expected__ = {"1 0",
                        "2 0",
                        "0 0"};

                return verifyCase(casenum__, expected__, new WordFind().findWords(grid, wordList));
            }
            case 1: {
                String[] grid = {"SXXX",
                        "XQXM",
                        "XXLA",
                        "XXXR"};
                String[] wordList = {"SQL", "RAM"};
                String[] expected__ = {"0 0",
                        ""};

                return verifyCase(casenum__, expected__, new WordFind().findWords(grid, wordList));
            }
            case 2: {
                String[] grid = {"EASYTOFINDEAGSRVHOTCJYG",
                        "FLVENKDHCESOXXXXFAGJKEO",
                        "YHEDYNAIRQGIZECGXQLKDBI",
                        "DEIJFKABAQSIHSNDLOMYJIN",
                        "CKXINIMMNGRNSNRGIWQLWOG",
                        "VOFQDROQGCWDKOUYRAFUCDO",
                        "PFLXWTYKOITSURQJGEGSPGG"};
                String[] wordList = {"EASYTOFIND", "DIAG", "GOING", "THISISTOOLONGTOFITINTHISPUZZLE"};
                String[] expected__ = {"0 0",
                        "1 6",
                        "0 22",
                        ""};

                return verifyCase(casenum__, expected__, new WordFind().findWords(grid, wordList));
            }

            // custom cases

            case 3: {
                String[] grid = {"PIYSRJFWOZ", "XMVFJYHKCX", "DYQCDELPKT", "BYYEPEDMLJ", "PJGXDHCZKC", "WCAWDYVSYP", "PFDATYSKMC", "OLCOLBOHEF", "ISCFLMSSVO", "UZALICRRGS", "ZQYWTPJGFV", "AJQHRMMJUG", "VUUATXYAIJ", "BIRTBMFMYR", "HJBGBXMHKB", "UJKJXYYEMO", "KCDPUWHACH", "CRYMRRFNMU", "GABUHJBCUT", "HNNWHSLPZG", "DZSNHRGITE", "NJGWCHCUDS", "LEUPKSMBVK", "QAXRSNOMGB", "IYPHOBFSMS", "ACBZJRQQPV", "CWPACIZXVL", "BQQVMTHEWU", "DDQNUSMMYS", "OJJNHCJALY", "HBBWIWFDQS"};
                String[] wordList = {"SNHRGIT", "XPCR", "E", "MGVD", "ZUIOPWPBDX", "K", "RJFW", "MM", "I", "VSY", "AC", "BSHW", "KPU", "Q", "QJ", "N", "Z", "YZDEJ", "CDPU", "WCYAEJZNARCJJIUJQZSLFC", "D", "Z", "DY"};
                String[] expected__ = {"20 2", "", "2 5", "", "", "1 7", "0 4", "11 5", "0 1", "5 6", "16 7", "", "", "2 2", "10 1", "17 7", "0 9", "", "16 1", "", "2 0", "0 9", "2 0"};

                return verifyCase(casenum__, expected__, new WordFind().findWords(grid, wordList));
            }
            case 4: {
                String[] grid = {"RDFHXVKDMDKUYTVWVBKWXRCKSHRRXROENDRNSAUWLOEGBVDEC", "JCWLKAETARDFWQTEXDVMPKGVZEKJRKWPFKLOPYGDCXGVKZWLA", "SDNQTQKCJDCMEHCHGMOIIBPEIWZLBYDFKICJJLYOUKOIZWKGT", "VTUUZXXAZBDLJACFJLXUCFANCAJIAHTYMJEVLWZAMGQROWJGC", "YKOZBCNCUVUCNKUITPYWGAIFWCGYLQLMTAFNENXICVXPXYJFD", "GSPVRAEFRTWOOXJGYFCKWQRHODVFYSCVFQCKQBEOOQUYUQSSH", "EDNVUCPCBHECVWBSHUTNOEDVMCMBRKJGPAIBJLHQFHQCULEXD", "ARAMCHSVEVXAINYAFNGKQSJDIJNOSLYTFWUXUHIEMWCTKLOZB", "YZYPCLITDCGPDXQVFLVGKVCKIFWOLGZZKZAFTYIZTLVZNBJEL", "RHHXKBHZYLRTDOKQXQKYDRKYFIUWNLTJDIVCFRQRYLRSKGSKI", "WBYCXMNCVYMKKXTMZVGJKUCWYLPFPIOZSDBZNPHSIIRXUCIJH", "EQBUECJYXQSDQVNFMNZCDZTWWRECRYLGTOKKFLBNZEBKBZOKA", "RGDMKVMKUJXBJJXMGQTHXRRNLKWAWTHQUYECIQFYHRSRJUHGB", "VJUHWLNTXDXEVYHCETJPUAIMECXTKLZFEUEIFWUJTSDUJJVYE", "JSXAAMPCNFGUKPAVNUVLYHOADBKBPFOBPMBXFHYONXSSEZUYD", "CPETXQYXEVORIGYYEVDQSVSRMVSUASRDMJBHOWQOVWNPBHBZC", "BJHPMTNIMNIREFBIZUPDOHRKHOLJYNESMGGWGUNUJTTVKQLCD", "ZSPYATKWKGCNMCLWLNLIZHJHCDJREUDSJQTIAIDKRZEHDHRAQ", "EFCOHJCFNFGZRWKMLPAKXBQATMOPGUSKECTPCHMSXGMDPSBCE", "AGJBNJDNZOANFTMOZQGFMGIILPXCBKUUXIBHAKQJOQPQIWMLV", "ZTEJFQVQXISIGGJDNPDXMEHCZMCPWCTMCENIDILTRRYYJDYRD", "EDPKKIOYUNJOYPWGSUVUBFSEAJGNDOJKZUMZANTUSJCRRZWTN", "NFZMCKRNNSOVFFRCRZYRYRVJEEAZXLVTFXLGFUNKWTEXYAETI", "MDZNEHZMZKNLMVOLJZAPRXTARGAUSFAFLGQJFMQVYOXPWZDRX", "KLEBFTLALWUGBDYFDHNRRNSLHVLNPOMAMFCUQPSCQBYNAFZGP", "OYKDVSYJYZIYBKBYYIUMJIIOGIKIKXXIETKEQIUPXFGITZLQI", "XZBGVDADYUHXGPESXNCDDLTJFDZVRPIYXGPLUYUIWQFVMUWWL", "RDNPGOZZXFKCWUOHNLBRDRNBVJIFVGILUOXHQXKPMHQTTUTDS", "VGBCJIOGZPXDBRXTSLTBMHBUNSDPCFMRAELHTLLWKUDCCQWLV", "IMWCHKREUVVOOHJJAJBONTNWALEDVIVFRAMOCQBOAJVWMRQCX", "KWPFLDRMHSZXJUCQXHPIVXXNFAXRQMNAACQDQRCIHEJOAFVLQ", "TAQZBMMZVJSFSXNVDKAHYLSYXCKGARDVGQSELQBDPBGKINNGK", "TLMXZUTJZDEDXZYDEEJZQKGVUAHDQFXHMDFMZSHYTJUKUBNXM", "SQOSRVWHRWDPSHGHRNOSQBAFUCUPOCXGZACEALCKUEEPGYRZU", "LANRQADADFQQFOIPNMIQPZWBGEZXKXKCAUPCMJITZNFLJGMGE", "QLPUXMECAAXELGGHVHYELOQQRODDLNOALUDPGATURAIVPJYGY", "VLGRPIDNYWAMEGCWQJOLGMIZEKWWMUYCOSFMGMMASUPVFYLTG", "LEXZZUXWFVQLLKFSXPFGTTBCBIVMGBBZOZAZXDHHZFGZPSDOF", "ZHLUYOLWUYTXZQBWNFBYLCUWQEWXUVLUVEOGCKYINNTNHKUMI", "WYTSEQYNUYZUEPTRFKUGMXPAAHTYHLPXKKZANBLMNXNQCHGDW", "TCLTQWVVEHAFHIUFHGJYKNATQELMVNQMJUSBKGMIDSOAPWOTJ", "GUJYHRXVPFGNQUMBNBJNYNZZTNSSJSJKQHTYXZKHLYHPVBHPY", "NOFCLENBACWZUMDBYZRPLPVISNJEAITUDNUVVGNGBDIWHMKEU", "YKLQYEZNVCWFYIPYFNIESSARSKETVZQVOLPAVEAKDCBDPCDYM", "SSAXYZWTJBTXQCCSCHFROCLJPYIRVZFGQFXGYVSDMRUCEGPJP", "QRWDWPKYPIPIUTMOYIDNFFUCFKVISLIGTZVRAYEJGRTJHQZTA"};
                String[] wordList = {"ZAEZBCJV", "SDE", "TNE", "SFLTYILGLK", "D", "MBRKJGPAIBJLHQFHQCUL", "CKGARDVGQSELQBDPBGK", "QX", "BKEHSJP", "UCUFABQSONRH", "CH", "PVRAEFRTW", "AAFEQ", "NBVJIFVGILUOX", "TCY", "SLYTFWUX", "MVOLJZAPRXTARGAUSFAFLGQJFMQVYOXPWZD", "HOQVTGFTODAETUAKA", "WPYOJNUYOIKKPD", "VMLEQ", "HGJYKNATQE", "DS", "EMGFPU", "TN", "KYFIUWNLTJD", "HKXV", "F", "E", "GQDDIIPCWLODMQTHDNN", "F", "QGZTWXSREILLWHQ", "YFHAQOKDXDNX", "MFYVEQZJDVJV", "BLQLKFLTJCBPJOMAIR", "B", "KIKXXIETKEQI", "XQVFLVGKVCKIF", "S", "BPHNW", "YLHQEYZPXQRZBLHJGVVFECKFNH", "GXLTLJFL", "EYZPXQRZBLHJGVVFECKFNHAMXAWKEXK", "ZWKSNIOF", "AYLWNBLHYRPLQWHWUIHKINUMPIYXLQRQSLJAMDKBGZG", "HGJYKNATQELMVN", "VFPDRGDPXDW", "RYZRCRFFVOSNNRKCMZF", "WVEATZPS", "EBDKTPACOCL", "T"};
                String[] expected__ = {"", "", "11 22", "", "0 1", "6 26", "31 25", "2 5", "24 12", "", "2 14", "5 2", "", "27 22", "", "7 28", "23 12", "", "", "", "40 16", "9 32", "20 33", "6 18", "9 22", "26 10", "0 2", "0 31", "4 20", "0 2", "", "", "", "", "0 17", "25 26", "8 13", "0 24", "", "", "36 2", "", "", "0 37", "40 16", "26 27", "", "", "", "0 13"};

                return verifyCase(casenum__, expected__, new WordFind().findWords(grid, wordList));
            }
/*      case 5: {
            String[] grid             = ;
			String[] wordList         = ;
			String[] expected__       = ;

			return verifyCase(casenum__, expected__, new WordFind().findWords(grid, wordList));
		}*/
            default:
                return -1;
        }
    }
}

// END CUT HERE
