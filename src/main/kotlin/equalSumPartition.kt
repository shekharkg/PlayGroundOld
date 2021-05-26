fun main() {
    val input = arrayOf(1, 5, 11, 5)
    val n = input.size
    var sum = 0

    for (i in 0 until n) sum += input[i]

    if (sum % 2 != 0) println("RESULT: FALSE")
    else println("RESULT: ${subsetSum(input, sum / 2, n)}")
}

private fun subsetSum(arr: Array<Int>, sum: Int, n: Int): Boolean {
    val t: Array<BooleanArray> = Array(n + 1) { BooleanArray(sum + 1) }

    for (i in 0..n) {
        for (j in 0..sum) {
            when {
                j == 0 -> t[i][j] = true
                i == 0 -> t[i][j] = false
                arr[i - 1] <= j -> t[i][j] = (t[i - 1][j - arr[i - 1]] || t[i - 1][j])
                else -> t[i][j] = t[i - 1][j]
            }
        }
    }
    return t[n][sum]
}