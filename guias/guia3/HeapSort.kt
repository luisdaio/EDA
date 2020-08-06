package guia3

class HeapSort(A: IntArray) {

    private fun buildMaxHeap(A: IntArray, heapSize: Int) {
        for (i in heapSize / 2 - 1 downTo 0) {
            MaxHeapify(A, heapSize, i)
        }
    }

    private fun MaxHeapify(A: IntArray, heapSize: Int, i: Int) {
        val l = left(i)
        val r = right(i)
        var largest = i
        if (l < heapSize && A[l] > A[largest]) {
            largest = l
        }
        if (r < heapSize && A[r] > A[largest]) {
            largest = r
        }
        if (largest != i) {
            exchange(A, i, largest)
            MaxHeapify(A, heapSize, largest)
        }
    }

    private fun exchange(A: IntArray, i: Int, j: Int) {
        val temp = A[i]
        A[i] = A[j]
        A[j] = temp
    }

    fun left(i: Int): Int {
        return 2 * i
    }

    fun right(i: Int): Int {
        return 2 * i + 1
    }

    init {
        val heapSize = A.size
        buildMaxHeap(A, heapSize)
        for (i in heapSize - 1 downTo 0) {
            exchange(A, 0, i)
            MaxHeapify(A, i, 0)
        }
    }
}

internal object MainHeapSort {
    @JvmStatic
    fun main(args: Array<String>) {
        val C = intArrayOf(4, 1, 3, 1, 16, 9, 10, 14, 8, 7)
        val A = C.clone()
        printArray(A)
        val hs = HeapSort(A)
        printArray(A)
    }

    private fun printArray(A: IntArray) {
        println()
        for (i in A.indices) {
            System.out.format("%3d", i)
        }
        println()
        for (i in A.indices) {
            System.out.format("%3d", A[i])
        }
        println()
    }
}