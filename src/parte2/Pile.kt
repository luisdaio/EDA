package parte2

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 20/05/20
 */

/**
 * Class Pile.
 * Represents a pile, also known as a stack.
 */
class Pile(n: Int) {
    var n = 0
    private var top: Int = -1
    private var pile:IntArray

    /**
     * Returns the state of the stack.
     * @return true if the stack is empty, false otherwise
     */
    private fun stackEmpty(): Boolean {
        return top == -1
    }

    /**
     * Inserts a new element in the stack.
     * @param x integer value to insert
     */
    fun push(x: Int) {
        top += 1
        pile[top] = x
    }

    /**
     * Removes the last element in the stack (LIFO policy).
     * @return the last element in the stack, 0 if the stack is empty
     */
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