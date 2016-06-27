package models

import modules.Dialogue
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

/**
  * Test the response model
  */
class DialogueSpec extends FlatSpec
  with Matchers
  with MockFactory {

  "Dialogue" should "return None if it does not know the msg" in {
    val dialogue = new Dialogue(Map.empty, (Int) => 0)
    dialogue.msg("hi") should be (None)
  }

  it should "use size 1 and return the first phrase" in {
    val nextInt = mockFunction[Int, Int]
    nextInt expects(1) returning (0)
    val dialogue = new Dialogue(Map("hi" -> List("Hello")), nextInt)
    dialogue.msg("hi") should be (Some("Hello"))
  }

  it should "use size 2 and return the second phrase" in {
    val nextInt = mockFunction[Int, Int]
    nextInt expects(2) returning (1)
    val dialogue = new Dialogue(Map("hi" -> List("Hello", "Hi")), nextInt)
    dialogue.msg("hi") should be (Some("Hi"))
  }

  it should "return the expected phrase from multiple msg" in {
    val nextInt = mockFunction[Int, Int]
    nextInt expects(2) returning (1)
    val dialogue = new Dialogue(Map("hi" -> List("Hello", "Hi"), "bye" -> List("See you", "bye")), nextInt)
    dialogue.msg("bye") should be (Some("bye"))
  }

}

