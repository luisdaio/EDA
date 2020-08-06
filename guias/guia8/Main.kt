package guia8

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 12/05/20
 */

fun main() {
    val n = 10;

    val bs =  RedBlackSearchTree(n);
    println("ARVORE RED BLACK");

    println(bs);
    var key = 12; bs.insert(key);
     key = 5; bs.insert(key);
     key = 18; bs.insert(key);
     key = 2; bs.insert(key);
     key = 9; bs.insert(key);
     key = 15; bs.insert(key);
     key = 19; bs.insert(key);
     key = 13; bs.insert(key);
     key = 17; bs.insert(key);

    println("\nAFTER\n$bs")
}