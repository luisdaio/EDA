package guia2;

import Utils.Utils;
import java.util.List;

public class MergeSortList {
    public void merge(List<Integer> A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = A.get(p + i);
        }

        for (int j = 0; j < n2; j++) {
            R[j] = A.get(q + j + 1);
        }

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                A.set(k, L[i++]);
            } else {
                A.set(k, R[j++]);
            }
        }
    }

    public void run(List<Integer> A, int p, int r) {
        if (p < r) {
            int q = (int) Math.floor((p + r) / 2.0);
            run(A, p, q);
            run(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    public static void main(String[] args) {
        MergeSortList m = new MergeSortList();
        List<Integer> ints = Utils.listWithRandomInts(10);
        m.run(ints, 0, ints.size() - 1);
        System.out.println(ints.toString());
    }
}
