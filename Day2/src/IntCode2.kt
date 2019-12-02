
class IntCode2(private val array: MutableList<Int>) {

    private var memory: MutableList<Int> = mutableListOf()
    private var currentPosition = 0

    fun run(end: Int): Pair<Int, Int>? {

        for (i in 0..99) {
            for (j in 0..99) {
                restore()
                setInitValues(i, j)

                while (memory[currentPosition] != Operation.Halt.value) {
                    calculate()
                    stepForward()
                }

                if (memory[0] == end) {
                    return Pair(i, j)
                }
            }
        }

        return null
    }

    private fun restore() {
        currentPosition = 0
        memory.clear()
        memory.addAll(array)
    }

    private fun setInitValues(first: Int, second: Int) {
        memory[1] = first
        memory[2] = second
    }

    private fun calculate() {
        val first = memory[currentPosition + 1]
        val second = memory[currentPosition + 2]
        val res = memory[currentPosition + 3]

        memory[res] = Operation.calculate(memory[currentPosition], memory[first], memory[second])
    }

    private fun stepForward() {
        currentPosition += Operation.getStep(memory[currentPosition])
    }

    private fun displayList() {
        memory.forEach {
            print("$it,")
        }
        println()
    }

    private enum class Operation(val value: Int, val step: Int, val func: (v1: Int, v2: Int) -> Int ) {
        Addition(1, 4, { v1, v2 -> v1 + v2 }),
        Multiplication(2, 4, { v1, v2 -> v1 * v2}),
        Halt(99, 1, { _, _ -> 0 });

        companion object {
            fun getStep(value: Int): Int {
                return Operation.values().first { it.value == value }.step
            }

            fun calculate(value: Int, v1: Int, v2: Int): Int {
                return Operation.values().first { it.value == value }.func(v1, v2)
            }
        }
    }
}