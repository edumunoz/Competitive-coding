import java.io.*;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        line = br.readLine();
        String[] numbers = line.split(" ");
        int[] diff = new int[numbers.length-1];
        for (int i = 0; i < numbers.length-1; i++) {
            diff[i] = Integer.parseInt(numbers[i+1]) - Integer.parseInt(numbers[i]);
        }

        int missing_index = N-2;

        for (int i = 0; i < diff.length-2; i++) {
            if (diff[i+1] == diff[i]*2) {
                missing_index = i;
            }
        }


        int add = missing_index != N-2 ? diff[missing_index] : diff[0];

        System.out.println(Integer.parseInt(numbers[missing_index+1]) + add);

    }

}