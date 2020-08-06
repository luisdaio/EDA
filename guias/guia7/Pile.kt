package guia7

class Pile(n: Int) {
    private var top: Int = -1
    private var pile = intArrayOf()

    init {
        pile = IntArray(n)
    }

    private fun stackEmpty(): Boolean {
        return top == -1
    }

    fun push(x: Int) {
        top++
        pile[top] = x
    }

    fun pop(): Int {
        return if (stackEmpty()) {
            println("underflow")
            0
        } else {
            top--
            pile[top + 1]
        }
    }

    override fun toString(): String {
        val s = StringBuilder()
        for (i in pile) {
            s.append(i).append(" ")
        }
        return s.toString()
    }
}