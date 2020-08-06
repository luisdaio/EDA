package guia4

import java.util.ArrayList

class QuickSortList (A : List<Int>, var sortedList : MutableList<Int> = A.toMutableList()){

    fun sort() : List<Int>{
        this.run(sortedList, 0, sortedList.size - 1)
        return sortedList
    }

    fun run(A: MutableList<Int>, p: Int, r: Int) {
        if (p < r) {
            var q = partition(A, p, r)
            run(A, p, q - 1)
            run(A, q + 1, r)
        }
    }

    private fun partition(A: MutableList<Int>, p: Int, r: Int): Int {
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

    private fun exchange(A: MutableList<Int>, i: Int, j: Int) {
        var temp = A[i]
        A[i] = A[j]
        A[j] = temp
    }
}