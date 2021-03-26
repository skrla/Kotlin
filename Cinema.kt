package cinema
import kotlin.math.round

fun main() {
  var seats = cinemaroom()
  while (true) {
    println("""
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit""")
    val x = readLine()!!.toInt()
    when (x) {
      1 -> showTheSeats(seats)
      2 -> seats = buyATicket(seats)
      3 -> statistics(seats)
      0 -> break
    }
  }
}

fun cinemaroom(): Array<Array<String>> {
  println("Enter the number of rows:")
  var rows = readLine()!!.toInt()
  println("Enter the number of seats in each row:")
  var coloms = readLine()!!.toInt()
  val seats = Array(rows + 1) {Array(coloms + 1) {"S"}} 
  for (i in 0..rows) {
    if (i == 0) {
      for (j in 0..coloms) {
        if (j == 0) {
          seats[i][j] = " "
      } else {
        seats[i][j] = "$j"
      }
      }
    } else if (i > 0) {
      seats[i][0] = "$i"
    }
  }
  return seats
}

fun showTheSeats(seats: Array<Array<String>>) {
  println("Cinema:")
  for (i in 0..seats.lastIndex) println(seats[i].joinToString(" "))
  println()
}

fun buyATicket(seats: Array<Array<String>>): Array<Array<String>> {
  do {
    println("Enter a row number:")
    val row = readLine()!!.toInt()
    println("Enter a seat number in that row:")
    val colom = readLine()!!.toInt()
    try {
      if (seats[row][colom] == "B") {
        println("That ticket has already been purchased!")
      } else {
          val half = seats.lastIndex / 2
          var price = priceOfSeat(half, row, seats)
          println()
          println("Ticket price: \$$price")
          seats[row][colom] = "B"
          break
        }
    } catch (e: Exception) {
      println("Wrong input!")
    }
  } while (true)
  
  return seats
}

fun statistics(seats: Array<Array<String>>) {
  var purchasedTickets = 0
  val half = seats.lastIndex / 2
  var price = 0
  var totalIncome = 0
  if (seats.lastIndex * seats[0].lastIndex <= 60) {
    totalIncome = seats.lastIndex * seats[0].lastIndex * 10
  } else {
    totalIncome = half * seats[0].lastIndex * 10
    totalIncome += (seats.lastIndex - half) * seats[0].lastIndex * 8
  }
  for (i in 1..seats.lastIndex) {
    for(j in seats[i]){
      if (j == "B") {
        ++purchasedTickets
        price += priceOfSeat(half, i, seats)
      }
    }
  }
  println()
  println("Number of purchased tickets: $purchasedTickets")
  if (purchasedTickets == 0) {
    println("Percentage: 0.00%")
  } else {
    var x = purchasedTickets.toDouble() / (seats.lastIndex * seats[0].lastIndex) * 100
    println("Percentage: ${"%.2f".format(x)}%")
  }
  println("Current income: \$$price")
  println("Total income: \$$totalIncome")
}

fun priceOfSeat(half: Int, row: Int, seats: Array<Array<String>>):Int {
  if (seats.lastIndex * seats[0].lastIndex <= 60 || half >= row) return 10 else return 8
}