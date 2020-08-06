package guia4

class QuickSortObject<T: Comparable<T>> (A: Array<T>) {
    init {
        val p = 0
        var r = A.size - 1
        this.run<T>(A, p, r)
    }

    fun <T: Comparable<T>> run(A: Array<T>, p: Int, r: Int) {
        if (p < r) {
            var q = partition(A, p, r)
            run(A, p, q - 1)
            run(A, q + 1, r)
        }
    }

    private fun <T: Comparable<T>> partition(A: Array<T>, p: Int, r: Int): Int {
        var x = A[r]
        var i = p - 1

        for (j in p until r) {
            if (A[j] <= x) {
                i++
                this.exchange(A, i, j)
            }
        }
        this.exchange(A, i + 1, r)
        return i + 1
    }

    private fun <T: Comparable<T>> exchange(A : Array<T>, i: Int, j: Int) {
        var temp = A[i]
        A[i] = A[j]
        A[j] = temp
    }
}