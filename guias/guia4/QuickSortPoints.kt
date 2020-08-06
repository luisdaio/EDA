package guia4

import Utils.Point

class QuickSortPoints (A : Array<Point>) {
    init {
        val p = 0
        var r = A.size - 1
        this.run(A, p, r)
    }

    fun run(A: Array<Point>, p: Int, r: Int) {
        if (p < r) {
            var q = partition(A, p, r)
            run(A, p, q - 1)
            run(A, q + 1, r)
        }
    }

    private fun partition(A: Array<Point>, p: Int, r: Int): Int {
        var x = A[r].distToOrigin
        var i = p - 1

        for (j in p until r) {
            if (A[j].distToOrigin <= x) {
                i++
                this.exchange(A, i, j)
            }
        }
        this.exchange(A, i + 1, r)
        return i + 1
    }

    private fun exchange(A: Array<Point>, i: Int, j: Int) {
        var temp = A[i]
        A[i] = A[j]
        A[j] = temp
    }
}