class SecureContainer2 {

    fun run(data: MutableList<String>) {
        val range = parse(data[0])
        print(generate(range).size)
    }

    private fun generate(range: Pair<Int, Int>): MutableList<Int> {
        val result = mutableListOf<Int>()

        for (index in range.first..range.second) {
            if (isValid(index.toString())) {
                result.add(index)
            }
        }

        return result
    }

    private fun isValid(value: String): Boolean {
        return hasDoubleDigit(value) && isIncreasing(value)
    }

    private fun hasDoubleDigit(value: String): Boolean {
        val list = IntArray(10) { 0 }

        value.forEach {
            val index = it.toInt() - 48
            list[index]++
        }

        return list.contains(2)
    }

    private fun isIncreasing(value: String): Boolean {
        for (i in 1 until value.length) {
            if (value[i - 1].toInt() > value[i].toInt()) {
                return false
            }
        }
        return true
    }

    private fun parse(data: String): Pair<Int, Int> {
        val delimiter = "-"
        val spliced = data.split(delimiter)

        return Pair(spliced.first().toInt(), spliced.last().toInt())
    }
}