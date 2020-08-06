fun main(args : Array<String>) {
    var array1 = arrayOf<Long>(1,5,7,9,60,3,4,45)
    var sortedArray = bubbleSort(array1.copyOf())
    print("Original array: ${array1.contentDeepToString()} \nSorted array: ${sortedArray.contentDeepToString()}")
}

fun bubbleSort(arr: Array<Long>):Array<Long>{
    var swap = true
    while(swap){
        swap = false
        for(i in 0 until arr.size-1){
            if(arr[i] > arr[i+1]){
                val temp = arr[i]
                arr[i] = arr[i+1]
                arr[i + 1] = temp

                swap = true
            }
        }
    }
    return arr
}