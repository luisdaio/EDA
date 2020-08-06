package guia9

class Pile<T>(n: Int) {
    private var top: Int = -1
    private var pile: Array<Any?> = Array(n) {}

    fun stackEmpty(): Boolean {
        return top == -1
    }

    fun push(x: T) {
        top++
        pile[top] = x
    }

    fun pop(): T? {
        return if (stackEmpty()) {
            println("underflow")
            null
        } else {
            top--
            pile[top + 1] as T
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