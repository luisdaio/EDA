package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public static int[] arrayWithRandomInts(int size) {
        Random m = new Random();
        int[] a = new int[size];
        for (int i = 0; i < size; i++){
            a[i] = m.nextInt();
        }
        return a;
    }

    public static List<Integer> listWithRandomInts(int size) {
        Random m = new Random();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < size; i++){
            a.add(m.nextInt());
        }
        return a;
    }
}
