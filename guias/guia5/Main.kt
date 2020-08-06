package guia5

fun main(args: Array<String>){
    println("Pilhas")
    var pile = Pile(10)
    pile.push(99)
    pile.push(10)
    pile.push(23)
    println(pile)
    var x = pile.pop()
    println(x)
    println(pile)

    var lista =  LinkedList(10);
    println(lista);
    lista.insert(10)
    lista.insert(9)
    lista.insert(88)
    lista.insert(77)
    lista.insert(567)
    println(lista)
    lista.delete(8)
    println(lista)
    lista.insert(666)
    println(lista)

    println("\n Pesquisa = " + lista.search(88))
}