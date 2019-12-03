import kotlin.math.abs

class CrossedWires2 {

    var currentNode = Node(0, 0, 0)
    var currentStep = 1

    fun run(array: MutableList<String>): Int {
        val firstVectorInstructions = parse(array[0])
        val secondVectorInstructions = parse(array[1])

        val firstVector = HashSet<Node>()
        val secondVector = HashSet<Pair<Int, Int>>()

        executeAddInstructions(firstVectorInstructions, firstVector)
        executeVerifyInstructions(secondVectorInstructions, firstVector, secondVector)

        return calculateLowestManhattanDistance(secondVector)
    }

    private fun calculateLowestManhattanDistance(vector: HashSet<Pair<Int, Int>>): Int {
        var lowest = 0
        vector.forEach {
            val distance = abs(it.first) + abs(it.second)

            if (lowest == 0) {
                lowest = distance
            } else {
                if (distance < lowest) {
                    lowest = distance
                }
            }
        }
        return lowest
    }

    private fun executeAddInstructions(firstVectorInstructions: MutableList<Pair<Char, Int>>, vector: HashSet<Node>) {
        firstVectorInstructions.forEach {
            for (counter in 1..it.second) {
                vector.add(createNode(it))
            }
        }
        clearCurrentState()
    }

    private fun executeVerifyInstructions(secondVectorInstructions: MutableList<Pair<Char, Int>>, firstVector: HashSet<Node>, secondVector: HashSet<Pair<Int, Int>>) {
        secondVectorInstructions.forEach {
            for (counter in 1..it.second) {
                val pair = createNode(it)
                if (firstVector.contains(pair)) {
                    val index = firstVector.indexOf(pair)
                    secondVector.add(Pair(firstVector.elementAt(index).steps, currentStep - 1))
                }
            }
        }
        clearCurrentState()
    }

    private fun clearCurrentState() {
        currentStep = 1
        currentNode = Node(0, 0, 1)
    }

    private fun createNode(instruction: Pair<Char, Int>?): Node {
        val node = when (instruction?.first) {
            'U' -> Node(currentNode.x, currentNode.y + 1, currentStep)
            'D' -> Node(currentNode.x, currentNode.y - 1, currentStep)
            'R' -> Node(currentNode.x + 1, currentNode.y, currentStep)
            'L' -> Node(currentNode.x - 1, currentNode.y, currentStep)
            else -> Node(0, 0, 0)
        }
        currentNode = node
        currentStep++
        return node
    }

    private fun parse(array: String): MutableList<Pair<Char, Int>> {
        val parsed = mutableListOf<Pair<Char, Int>>()
        val delimiter = ','

        array.split(delimiter).forEach {
            val instruction = it.first()
            val value = it.substring(1, it.length).toInt()

            parsed.add(Pair(instruction, value))
        }

        return parsed
    }
}