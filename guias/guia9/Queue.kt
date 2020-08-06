package guia9

class Queue<T>(var n: Int) {
    private var queue:Array<Any?> = Array(n){}
    var length: Int = n-1
    var tail: Int = 0
    var head: Int = 0

    fun enqueue(x: T){
        queue[tail] = x
        if (tail == length) {
            tail = 0
        } else {
            tail++
        }
    }

    fun dequeue(): T {
        var x = queue[head] as T
        if (head == length){
            head = 0
        } else {
            head++
        }
        return x
    }
}