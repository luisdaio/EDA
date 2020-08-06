package guia8

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 05/05/20
 */

class BinaryTreeMemory(n: Int) {
    var key: IntArray
    var left:  IntArray
    var right: IntArray
    var parent: IntArray
    private val NIL = 0
    private var pile: Pile

    init {
        this.parent = IntArray(n)
        this.key = IntArray(n)
        this.left = IntArray(n)
        this.right = IntArray(n)
        this.pile = Pile(n)
        for (i in 1 until n) {
            this.pile.push(i)
        }

        for (i in 0 until n){
            this.parent[i] = this.NIL
            this.right[i] = this.NIL
            this.left[i] = this.NIL
        }
    }

    fun allocateObject(): Int{
        val x = pile.pop()
        return if (x == 0){
            0
        } else{
            x
        }
    }

    fun freeObject(x: Int){
        this.pile.push(x)
    }

    override fun toString(): String {
        val s = StringBuilder()
        for (k in parent.size - 1 downTo 0) {
            s.append("$k -> [parent = ${if (parent[k] == 0) "nil" else parent[k]}," +
                    " key = ${key[k]}, left = ${if (left[k] == 0) "nil" else left[k]}, " +
                    "right = ${if (right[k] == 0) "nil" else right[k]}]\n")
        }
        return s.toString()
    }
}