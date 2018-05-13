import java.util.LinkedList

fun main(args: Array<String>) {

    readLine()
    var lastKey = ""
    var numValues = 0
    val table = HashMap<String, Int>()
    val array = readLine()!!.split(" ")

    array.map {
        table.putIfAbsent(it,  1)
        if (table.containsKey((it.toInt() - 1).toString())) table[it] = table[(it.toInt() - 1).toString()]!! + 1
        val tempValue = table[it]!!
        if(tempValue > numValues) {
            numValues = tempValue
            lastKey = it
        }
    }

    println(numValues)

    val output = StringBuilder()
    val stack = LinkedList<Int>()
    for (i in array.size - 1 downTo 0)
        if (array[i] == lastKey){
            stack.addFirst(i + 1)
            lastKey = (lastKey.toInt() - 1).toString()
        }

    stack.forEach { output.append(it).append(" ") }
    print(output.deleteCharAt(output.length - 1))

}