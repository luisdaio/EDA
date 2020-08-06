package guia5

class Queue(var n: Int) {
    var queue = IntArray(n)
    var length: Int = n-1
    var tail: Int = 0
    var head: Int = 0

    fun enqueue(x: Int){
        queue[tail] = x
        if (tail == length) {
            tail = 0
        } else {
            tail++
        }
    }

    fun dequeue(): Int {
        var x = queue[head]
        if (head == length){
            head = 0
        } else {
            head++
        }
        return x
    }
}