
class Node(val x: Int, val y: Int, val steps: Int) {

    override fun toString(): String {
        return "Node=(x:$x, y:$y)"
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }
}