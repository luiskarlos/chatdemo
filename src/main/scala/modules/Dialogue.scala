package modules

import scala.util.Random

/**
  * Simple Model to response Messages
  */
class Dialogue(data:Map[String, List[String]], random:(Int) => Int) {

  def msg(msg:String) : Option[String] = {
    data.get(msg) match {
      case Some(phrases) => Some(phrases(random(phrases.length)))
      case None => None
    }
  }

}

object Dialogue {

  private val data = Map(
    "Hi" -> List("Yo, what's up","hey you again!"),
    "How are you?" -> List("I don't know I'm a machine","Kinda electric tbh"),
    "Bye" -> List("Cya!","Great talking to you")
  )

  def apply(): Dialogue = new Dialogue(data, Random.nextInt)
}