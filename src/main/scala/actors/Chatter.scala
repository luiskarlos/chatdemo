package actors

import akka.actor.Actor
import modules.Dialogue

/**
 * Chatter uses the model.Dialogue to response the msg.
 * This creates a clean separation between the the Actors and the chat functionality
 *  - Makes the system really easy to test
 *  - The model is reusable
 */
class Chatter(dialog:Dialogue) extends Actor {

  override def receive: Receive = {
    case msg: String => {
      println("remote received " + msg + " from " + sender)
      sender ! dialog.msg(msg.toString).getOrElse("unknown")
    }
    case _ => println("Received unknown msg ")
  }

}
