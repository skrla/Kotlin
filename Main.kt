package tictactoe

fun main() {
    val field = settingPlayground()
    playground(field)
    game(field)
}

fun settingPlayground() = Array(3) {Array(3) {" "}}


fun playground(field: Array<Array<String>>) {
    println("---------")
    for (i in 0..2) {
        println("| ${field[i].joinToString(" ")} |")
    }
    println("---------")
}

fun game(field: Array<Array<String>>): Array<Array<String>> {
    var count = 0
    loop@do {
        var row = ""
        var column = ""
        println("Enter the coordinates:")
        val x = readLine()!!.replace(" ", "")
        for (i in x) {
            if (i !in '0'..'9') {
                println("You should enter numbers! ")
                continue@loop
            } else if (i < '0' || '3' < i) {
                println("Coordinates should be from 1 to 3!")
                continue@loop
            } else if (row == "") {
                row = i.toString()
                continue
            } else {
                column = i.toString()
            }
        }
        if (field[row.toInt() - 1][column.toInt() - 1] != " ") {
            println("This cell is occupied! Choose another one!")
            continue
        } else {
            count++
            if (count % 2 == 0) {
                field[row.toInt() - 1][column.toInt() - 1] = "O"
                playground(field)
            } else {
                field[row.toInt() - 1][column.toInt() - 1] = "X"
                playground(field)
            }
            if (count < 5) {
                continue@loop
            } else if(win(field)) {
                if (count % 2 == 0) println("O wins") else println("X wins")
                break
            } else if (count == 9 && !win(field)) {
                println("Draw")
                break
            }
        }
    } while (true)
    return field
}

fun win(field: Array<Array<String>>): Boolean {
    val winnerX = winX(field)
    val winnerO = winO(field)
    var winner = false
    if (winnerX || winnerO) winner = true
    return winner
}

fun winX(field: Array<Array<String>>): Boolean {
    val xWins = "XXX"
    for (i in 0..field.lastIndex) {
        if (xWins == field[i].joinToString()) return true
    }
    when (xWins) {
        field[0][0] + field[1][0] + field[2][0] -> return true
        field[0][1] + field[1][1] + field[2][1] -> return true
        field[0][2] + field[1][2] + field[2][2] -> return true
        field[0][0] + field[1][1] + field[2][2] -> return true
        field[0][2] + field[1][1] + field[2][0] -> return true
    }
    return false
}

fun winO(field: Array<Array<String>>): Boolean {
    val oWins = "OOO"
    for (i in 0..field.lastIndex) {
        if (oWins == field[i].joinToString()) return true
    }
    when (oWins) {
        field[0][0] + field[1][0] + field[2][0] -> return true
        field[0][1] + field[1][1] + field[2][1] -> return true
        field[0][2] + field[1][2] + field[2][2] -> return true
        field[0][0] + field[1][1] + field[2][2] -> return true
        field[0][2] + field[1][1] + field[2][0] -> return true
    }
    return false
}