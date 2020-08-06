package guia7

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 05/05/20
 */

class BinaryTreeMemory(var n: Int) {
    var key = IntArray(n)
    var left = IntArray(n)
    var right = IntArray(n)
    var parent = IntArray(n)
    val NIL = 0
    var pile = Pile(n)

    init {
        for (i in 1 until n) {
            pile.push(i)
        }

        for (i in key.indices){
            this.parent[i] = this.NIL
            this.right[i] = this.NIL
            this.left[i] = this.NIL
        }
    }

    fun allocateObject(): Int{
        var x = pile.pop()
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
        var s = StringBuilder()
        for (k in parent.size - 1 .. 0) {
            s.append(k);
            s.append("-> [parent = ");
            s.append(parent[k]);
            s.append(", key = ")
            s.append(key[k]);
            s.append(", left = ");
            s.append(left[k]);
            s.append(", child = ");
            s.append(right[k]);
            s.append("]\n");
        }
        return s.toString()
    }
}