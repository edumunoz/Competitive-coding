import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

/**
 * Created by edu on 5/1/14.
 */
public class Skyline {

    public static List<Integer> CalculateSkyline(ArrayList<Triple> buildings) {
        int[] skyline = new int[10000];

        for (Triple b : buildings) {
            for (int i = b.l; i < b.r; i++) {
                if (skyline[i] < b.h) {
                    skyline[i] = b.h;
                }
            }
        }

        int currentHeight = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < skyline.length; i++) {
            if (skyline[i] == currentHeight)
                continue;
            currentHeight = skyline[i];
            result.add(i);
            result.add(currentHeight);
        }

        return result;
    }

    public static List<Integer> CalculateSkyline2(List<Triple> buildings) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if (buildings.size() == 1) {
            result.add(buildings.get(0).l);
            result.add(buildings.get(0).h);
            result.add(buildings.get(0).r);
            result.add(0);
        } else {
            List<Integer> subA = CalculateSkyline2(buildings.subList(0, buildings.size() - 1 / 2));
            List<Integer> subB = CalculateSkyline2(buildings.subList(buildings.size() + 1 / 2, buildings.size() - 1));

            int subAx = 0, subAh = 0, subBx = 0, subBh = 0;

            while (!subA.isEmpty() && !subB.isEmpty()) {
                if (subA.get(0) < subB.get(0)) {
                    subAx = subA.remove(0);
                    subAh = subA.remove(0);
                }


            }
        }

        return result;
    }

    static class Triple {
        int l, r, h;

        Triple(int l, int h, int r) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    public static void main(String[] args) {
        Triple[] b = {
                new Triple(1, 11, 5),
                new Triple(2, 6, 7),
                new Triple(3, 13, 9),
                new Triple(12, 7, 16),
                new Triple(14, 3, 25),
                new Triple(19, 18, 22),
                new Triple(23, 13, 29),
                new Triple(24, 4, 28)
        };

        ArrayList<Triple> buildings = new ArrayList<Triple>(Arrays.asList(b));

        List<Integer> result = Skyline.CalculateSkyline(buildings);
        for (int r : result) {
            System.out.print(r + " ");
        }
    }

}
