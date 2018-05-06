fun main (args: Array<String>) {

    var counter = 0
    var index = readLine()!!.toInt()

    while(index > 0) index -= (++counter).toString().length
    println(counter.toString()[index + (counter.toString().length - 1)])

}

