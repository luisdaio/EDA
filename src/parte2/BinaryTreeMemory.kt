package parte2

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 20/05/20
 */

/**
 * Binary Tree Memory
 * Represents the memory for a binary search tree, using a pile (a.k.a. stack).
 */
class BinaryTreeMemory(size: Int) {
    var parent: IntArray
    var key: Array<String?>
    var left: IntArray
    var right: IntArray
    private val NIL = 0
    private var pile: Pile

    /**
     * Allocates a space in memory.
     */
    fun allocateObject(): Int {
        val x = pile.pop()
        return if (x == 0) {
            0
        } else {
            x
        }
    }

    /**
     * Updates the memory's "free list".
     */
    private fun freeObject(x: Int) {
        this.pile.push(x)
    }

    override fun toString(): String {
        val s = StringBuilder()
        s.append("\n")
        for (k in parent.size - 1 downTo 1) {
            s.append(
                "$k -> [parent = ${if (parent[k] == 0) "nil" else parent[k]}," +
                        " key = ${key[k]}, left = ${if (left[k] == 0) "nil" else left[k]}, " +
                        "right = ${if (right[k] == 0) "nil" else right[k]}]\n"
            )
        }
        return s.toString()
    }

    init {
        this.parent = IntArray(size)
        this.key = Array(size) { null }
        this.left = IntArray(size)
        this.right = IntArray(size)
        this.pile = Pile(size)
        for (i in 1 until size) {
            this.freeObject(i)
        }

        for (i in 0 until size) {
            this.parent[i] = this.NIL
            this.right[i] = this.NIL
            this.left[i] = this.NIL
        }
    }
}