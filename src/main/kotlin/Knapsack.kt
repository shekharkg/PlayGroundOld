var counter = 0
val matrix: Array<IntArray> = Array(5) { IntArray(8) { -1 } }

fun main() {
    val weight = arrayOf(1, 3, 4, 5)
    val value = arrayOf(1, 4, 5, 7)
    val capacity = 7


//    println("knapsackWithRecursion RESULT: ${knapsackWithRecursion(weight, value, capacity, weight.size)}")
//    println("knapsackWithRecursionAndMemoization RESULT: ${knapsackWithRecursionAndMemoization(weight, value, capacity, weight.size)}")
    println("knapsackWithTopDown RESULT: ${knapsackWithTopDown(weight, value, capacity, weight.size)}")

//    println("Counter: $counter")
//    for (arr in matrix)
//        println(arr.contentToString())


}

fun knapsackWithTopDown(wt: Array<Int>, v: Array<Int>, w: Int, n: Int): Int {
    for (i in 0..n)
        for (j in 0..w) {
            matrix[i][j] = when {
                i == 0 || j == 0 -> 0
                wt[i - 1] <= j -> maxOf(v[i - 1] + matrix[i - 1][j - wt[i - 1]], matrix[i - 1][j])
                else -> matrix[i - 1][j]
            }
            counter++
        }

    return matrix[n][w]
}


fun knapsackWithRecursion(w: Array<Int>, v: Array<Int>, W: Int, n: Int): Int {
    println("Counter: ${counter++}, W -> $W & n: $n")
    if (n == 0 || W == 0) return 0

    return when {
        w[n - 1] <= W -> maxOf(
            v[n - 1] + knapsackWithRecursion(w, v, W - w[n - 1], n - 1),
            knapsackWithRecursion(w, v, W, n - 1)
        )
//        w[n - 1] > W
        else -> knapsackWithRecursion(w, v, W, n - 1)
    }
}

fun knapsackWithRecursionAndMemoization(w: Array<Int>, v: Array<Int>, W: Int, n: Int): Int {
    println("Counter: ${counter++}, W -> $W & n: $n")
    if (n == 0 || W == 0) return 0

    if (matrix[n][W] != -1) return matrix[n][W]



    return when {
        w[n - 1] <= W -> {
            matrix[n][W] = maxOf(
                v[n - 1] + knapsackWithRecursionAndMemoization(w, v, W - w[n - 1], n - 1),
                knapsackWithRecursionAndMemoization(w, v, W, n - 1)
            )
            matrix[n][W]
        }
        //w[n - 1] > W
        else -> {
            matrix[n][W] = knapsackWithRecursionAndMemoization(w, v, W, n - 1)
            matrix[n][W]
        }
    }
}