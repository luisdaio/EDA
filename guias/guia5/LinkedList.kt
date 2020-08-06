package guia5

class LinkedList(val n: Int) {
    var memory = LinkedListMemory(n)
    val NIL = Integer.MAX_VALUE
    var head: Int = NIL

    fun insert(key: Int) {
        var x = memory.allocateObject()
        memory.next[x] = head
        memory.key[x] = key

        if (head != NIL) {
            memory.previous[head] = x
        }

        head = x
        memory.previous[x] = NIL
    }

    fun delete(x: Int) {
        if (memory.previous[x] != NIL) {
            memory.next[memory.previous[x]] = memory.next[x]
        } else {
            head = memory.next[x]
        }

        if (memory.next[x] != NIL){
            memory.previous[memory.next[x]] = memory.previous[x]
        }
    }

    fun search(k: Int): Int{
        var x = head
        while (x != NIL && memory.key[x] != k) {
            x = memory.next[x]
        }
        return x
    }

    override fun toString(): String {
        return "$memory head -> $head"
    }
}