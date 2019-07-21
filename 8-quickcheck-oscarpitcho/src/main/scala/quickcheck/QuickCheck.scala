package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

   lazy val genHeap: Gen[H] =oneOf(const(empty), for {
    x <- arbitrary[Int]
    h <- oneOf(genHeap, const(empty))
  } yield insert(x, h) )

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("Insert 2") = forAll{ (x: Int, y: Int) => findMin(insert(y, insert(x, empty))) == Math.min(x, y)}

  property("Insert 1") = forAll( (x: Int) => deleteMin(insert(x, empty)) == empty)

  property("Sorted") = false

  property("Union min") = false 

}
