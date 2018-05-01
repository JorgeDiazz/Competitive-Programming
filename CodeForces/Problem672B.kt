fun main (args: Array<String>){

    val length = readLine()!!.toInt()
    val array = IntArray(26)
    readLine()!!.toCharArray().map { x -> array[x - 'a']++ }

    var filtredArray = array.filter { x -> x > 1 }.map { x -> x - 1 }
    if (filtredArray.isEmpty()) {
        println(0)
    } else {
        val dif = filtredArray.sum()
        println(if (dif > 25 || length > 26) -1 else dif)
    }
}