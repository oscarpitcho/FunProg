package recfun

object Main {
  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if(r == 1 || c == r || c == 0)
        1
      else
       pascal(c - 1, r - 1) + pascal(c, r - 1) 
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      balanceRecursion(0, chars)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if (money == 0)
        1
      else if (money < 0 || coins.isEmpty)
        0
      else 
        countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }

    def balanceRecursion(lOpen: Int, chars: List[Char]): Boolean = {
      if( lOpen < 0 || chars.isEmpty && lOpen > 0)
        false
      else if(lOpen == 0 && chars.isEmpty)
        true
      else if(chars.head == '(')
        balanceRecursion(lOpen + 1, chars.tail)
      else if(chars.head == ')')
        balanceRecursion(lOpen - 1, chars.tail)
      else
        balanceRecursion(lOpen, chars.tail)
    }
  }
