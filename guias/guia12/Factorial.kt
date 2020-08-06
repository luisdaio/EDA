package guia12

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 16/06/20
 */
class Factorial(val n: Int) : Runnable {
    private var res = 0

    fun getRes(): Int {
        return this.res
    }

    private fun calculate(n: Int): Int {
        return if(n <= 0){
            1
        } else{
            n * calculate(n - 1)
        }
    }

    override fun run() {
        this.res = this.calculate(this.n)
    }
}