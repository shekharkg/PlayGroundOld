fun main() {

    var counter = 0

    val arr = arrayOf(2, 3, 5, 8, 11)
    val n = arr.size
    val sum = 11

    val t: Array<BooleanArray> = Array(n + 1) { BooleanArray(sum + 1) }

    for (i in 0..n) {
        for (j in 0..sum) {
            counter++
            when {
                j == 0 -> t[i][j] = true
                i == 0 -> t[i][j] = false
                arr[i - 1] <= j -> t[i][j] = (t[i - 1][j - arr[i - 1]] || t[i - 1][j])
                else -> t[i][j] = t[i - 1][j]
            }
        }
    }


    for (item in t)
        println(item.contentToString())
    println("Counter: $counter")
}