package guia5

class LinkedListMemory(var n: Int) {
    var next = IntArray(n)
    var key = IntArray(n)
    var previous = IntArray(n)
    var pile = Pile(n)

    init {
        for (i in 1 until n) {
            pile.push(i)
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
        pile.push(x)
    }

    override fun toString(): String {
        var s = StringBuilder()
        for (k in next.indices) {
            s.append(k);
            s.append("-> [");
            s.append(previous[k]);
            s.append(", ");
            s.append(key[k]);
            s.append(", ");
            s.append(next[k]);
            s.append("]\n");
        }
        return s.toString()
    }
}