package interpreter

object Main {
  import java.io.{BufferedReader, InputStreamReader}
  val in = new BufferedReader(new InputStreamReader(System.in))

def main(args: Array[String]): Unit = {
   def repl(): Unit= {
      print("lisp>")
      val line = in.readLine
      val finish = line.replace(" ", "").toUpperCase
      if (finish != "EXIT"){
        println(Lisp.evaluate(Lisp.string2lisp(line)))
      } else{
        System.exit(0)
      }
    repl()
    }
    repl()
  }
}


object LispCode {
    // TODO: implement the function `reverse` in Lisp.
  // From a list (a, b, c, d) it should compute (d, c, b, a)
  // Write it as a String, and test it in your REPL
  val reverse = """
            def(reverse L acc) (if (null? L) acc (reverse (cdr L) (cons (car L) acc)))
           """

    // TODO: implement the function `differences` in Lisp.
  // From a list (a, b, c, d ...) it should compute (a, b-a, c-b, d-c ...)
  // You might find useful to define an inner loop def
  val differences = 
  """
    def (differences L) (
      def (aux L1 L2 acc) 
        (if (null? L1) 
          (reverse acc nil)
          (aux (cdr L1) (cdr L2) (cons (- (car L1) (car L2)) acc ))
        )
      (aux L (cons 0 L) nil)
    )
  """
  
  val rebuildList = 
  """
  def (rebuildList L) (
    def (aux L1 L2) 
      (if (null? L1) 
        (cdr (reverse L2 nil))
        (aux (cdr L1) (cons (+ (car L1) (car L2)) L2))
      )
    (aux L (cons 0 nil))
  )
  
  """

  val withDifferences: String => String =
    (code: String) => "(" + reverse + " (" + differences + " (" + rebuildList + " " + code + ")))"
}
