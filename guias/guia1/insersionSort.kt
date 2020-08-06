fun main(args : Array<String>) {
    var array1 = arrayOf<Long>(1,5,7,9,60,3,4,45)
    var sortedArray = insertionSort(array1.copyOf())
    print("Original array: ${array1.contentDeepToString()} \nSorted array: ${sortedArray.contentDeepToString()}")
}

fun insertionSort(items: Array<Long>) : Array<Long>{


    if (items.isEmpty() || items.size<2){
        return items
    }
    for (count in 1..items.count() - 1){
        // println(items)
        val item = items[count]
        var i = count
        while (i>0 && item < items[i - 1]){
            items[i] = items[i - 1]
            i -= 1
        }
        items[i] = item
    }
    return items

}