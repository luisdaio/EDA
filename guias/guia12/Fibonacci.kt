package guia12

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 16/06/20
 */
class Fibonacci(val n: Int) : Runnable{
    private var res = 0

    fun getRes(): Int {
        return this.res
    }

    private  fun recursiveCalculate(n: Int): Int {
        return if (n == 0) {
            0
        } else if (n == 1 || n == 2) {
            1
        } else {
            recursiveCalculate(n - 2) + recursiveCalculate(n - 1)
        }
    }

    override fun run() {
        this.res = this.recursiveCalculate(this.n)
    }
}