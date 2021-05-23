var counter = 0
val matrix: Array<IntArray> = Array(8) { IntArray(8) { -1 } }

fun main() {
    val weight = arrayOf(1, 3, 4, 5)
    val value = arrayOf(1, 4, 5, 7)
    val capacity = 7


    println("knapsackWithRecursion RESULT: ${knapsackWithRecursion(weight, value, capacity, weight.size)}")
    println("knapsackWithRecursionAndMemoization RESULT: ${knapsackWithRecursionAndMemoization(weight, value, capacity, weight.size)}")
}

fun knapsackWithRecursion(w: Array<Int>, v: Array<Int>, W: Int, n: Int): Int {
    println("Counter: ${counter++}, W -> $W & n: $n")
    if (n == 0 || W == 0) return 0

    return when {
        w[n - 1] <= W -> maxOf(
            v[n - 1] + knapsackWithRecursion(w, v, W - w[n - 1], n - 1),
            knapsackWithRecursion(w, v, W, n - 1)
        )
        w[n - 1] > W -> knapsackWithRecursion(w, v, W, n - 1)
        else -> 0
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
        w[n - 1] > W -> {
            matrix[n][W] = knapsackWithRecursionAndMemoization(w, v, W, n - 1)
            matrix[n][W]
        }
        else -> 0
    }
}