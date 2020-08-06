package guia8

class Pile(n: Int) {
    var n = 0
    private var top: Int = -1
    private var pile:IntArray

    private fun stackEmpty(): Boolean {
        return top == -1
    }

    fun push(x: Int) {
        top = top + 1
        pile[top] = x
    }

    fun pop(): Int {
        return if (stackEmpty()) {
            println("underflow")
            0
        } else {
            top = top - 1
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

    init {
        this.n = n
        this.top = - 1
        pile = IntArray(this.n)
    }
}