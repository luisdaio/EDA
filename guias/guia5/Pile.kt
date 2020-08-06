package guia5

class Pile(var n: Int) {
    private var top: Int = -1
    var s = intArrayOf()

    init {
        s = IntArray(n)
    }

    private fun stackEmpty(): Boolean {
        return top == -1
    }

    fun push(x: Int) {
        top++
        s[top] = x
    }

    fun pop(): Int {
        return return if (stackEmpty()) {
            println("underflow")
            0
        } else {
            top--
            s[top + 1]
        }
    }

    override fun toString(): String {
        var s = StringBuilder()
        for (i in s) {
            s.append(i).append(" ")
        }
        return s.toString()
    }
}