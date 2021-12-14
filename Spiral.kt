import javax.swing.*

fun main() {
    val x = JOptionPane.showInputDialog("Unesite broj redaka").toInt()
    val y = JOptionPane.showInputDialog("Unesite broj stupaca")!!.toInt()
    spiralValues(x, y)
}

fun spiralValues(row: Int, colum: Int) {
    var x = row - 1
    var y = colum - 1
    var downTo = 0
    var upTo = 1
    val spiral = Array(row) { Array(colum) { 0 } }
    var number = getNumber(spiral)
    while (number < (row * colum)) {
        forDownY(x, y, downTo, number, spiral)
        x -= 1
        number = getNumber(spiral)
        forDownX(x, downTo, number, spiral)
        number = getNumber(spiral)
        forUpY(upTo, y, downTo, number, spiral)
        ++downTo
        number = getNumber(spiral)
        forUPX(upTo, x, y, number, spiral)
        y -= 1
        ++upTo
        number = getNumber(spiral)
    }
    for (i in 0 until spiral.lastIndex + 1) {
        println(spiral[i].joinToString(" "))
    }
}

fun forDownY(x: Int, y:Int, countDown: Int, number: Int, spiral: Array<Array<Int>>): Array<Array<Int>> {
    var value = number
    for (i in y downTo  countDown) {
        spiral[x][i] = ++value
    }
    return spiral
}

fun forDownX(x: Int, countDown: Int, number: Int, spiral: Array<Array<Int>>): Array<Array<Int>> {
    var value = number
    for (i in x downTo countDown) {
        spiral[i][countDown] = ++value
    }
    return spiral
}
fun forUpY(x: Int, y: Int, countDown: Int, number: Int, spiral: Array<Array<Int>>): Array<Array<Int>> {
    var value = number
    for (i in x..y) {
        spiral[countDown][i] = ++value
    }
    return spiral
}

fun forUPX(upTo: Int, x:Int, y:Int, number:Int, spiral: Array<Array<Int>>): Array<Array<Int>> {
    var value = number
    for(i in upTo..x) {
        spiral[i][y] = ++value
    }
    return spiral
}

fun getNumber(spiral: Array<Array<Int>>): Int {
    var x = 0
    for (i in 0..spiral.lastIndex) {
        for (j in 0..spiral[i].lastIndex) {
            if (spiral[i][j] > x) {
                x = spiral[i][j]
            }
        }
    }
    return x
}
