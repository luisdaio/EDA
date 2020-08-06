package parte2

import java.io.File

/**
 * Realizado por: Luís Fernandes
 * N.º: 17186
 * E-mail: 17186@stu.ipbeja.pt
 * 20/05/20
 */

fun main() {
    val filename = "src/parte2/loremIpsum.txt"
    val file = File(filename)
    val text = file.readText(Charsets.UTF_8)
    var textArray = text.replace("[\\p{P}]".toRegex(), " ").split("\\s+".toRegex())
    textArray = textArray.distinct()
    val removeAll = textArray.toMutableList()
    removeAll.removeAll(listOf(""))
    textArray = removeAll.toList()
    val redBlackBTS = RedBlackSearchTree(textArray.size)
    for (word in textArray) redBlackBTS.insert(word)
    print(redBlackBTS)
}