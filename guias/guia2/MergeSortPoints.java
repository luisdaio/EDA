package guia2;

public class MergeSortPoints {

    public void merge(Point[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        Point[] L = new Point[n1 + 1];
        Point[] R = new Point[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = A[q + j + 1];
        }

        L[n1] = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        R[n2] = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (L[i].getDistToOrigin() <= R[j].getDistToOrigin()) {
                A[k] = L[i++];
            } else {
                A[k] = R[j++];
            }
        }
    }

    public void run(Point[] A, int p, int r) {
        if (p < r) {
            int q = (int) Math.floor((p + r) / 2.0);
            run(A, p, q);
            run(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    public static void main(String[] args) {
        MergeSortPoints m = new MergeSortPoints();

        Point[] points =  {new Point(2, 2, 2), new Point(3, 3, 3), new Point(1, 1, 1), new Point(0.5, 0.5, 0.5)};
        printPoints(points);
        m.run(points, 0, points.length - 1);
        printPoints(points);
    }

    public static void printPoints(Point[] points) {

        System.out.print("\n[");

        for (Point p : points) {
            System.out.format("%s, ",p.toString());
        }

        System.out.print("]\n");
    }
}


