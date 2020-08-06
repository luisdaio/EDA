package guia2;

import Utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;

public class MergeSort {
    public void merge(int[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = A[q + j + 1];
        }

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i++];
            } else {
                A[k] = R[j++];
            }
        }
    }

    public void run(int[] A, int p, int r) {
        if (p < r) {
            int q = (int) Math.floor((p + r) / 2.0);
            run(A, p, q);
            run(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    public static void main(String[] args) {
        MergeSort m = new MergeSort();
        final String filename = "src/guia2/mergeSortTimes.txt";
        try {

            FileWriter fileWriter = new FileWriter(filename);
            for (int a = 1000000; a <= 30000000; a += 1000000) {
                int[] ints = Utils.arrayWithRandomInts(a);
                long timeElapsed = 0;

                for (int i = 0; i < 50; i++) {
                    int[] clone = ints.clone();
                    long startTime = System.nanoTime();
                    m.run(clone, 0, ints.length - 1);
                    long endTime = System.nanoTime();
                    timeElapsed += (endTime - startTime) / 1000000000;
                }

                timeElapsed /= 50;

                fileWriter.append(a + " " + timeElapsed + "\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
