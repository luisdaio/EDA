package Utils

import java.util.*

object Utils {
    fun arrayWithRandomInts(size: Int): IntArray {
        val m = Random()
        val a = IntArray(size)
        for (i in 0 until size) {
            a[i] = m.nextInt()
        }
        return a
    }

    fun listWithRandomInts(size: Int): List<Int> {
        val m = Random()
        val a: MutableList<Int> = ArrayList()
        for (i in 0 until size) {
            a.add(m.nextInt())
        }
        return a
    }
}