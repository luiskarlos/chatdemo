package actors

import akka.actor._

import scala.concurrent.duration.Duration

/**
 * Send Messages in order to the Chatter.
 * This simple implementation of states to maintain the order
 */
class Asker(chatterRef:ActorSelection) extends Actor {

  override def receive: Receive = {
    case "Start" => gotoStateAndSendMsg("", waitingHiResponse, "Hi")
  }

  def waitingHiResponse: Receive = {
    case e: Exception => throw e
    case msg: String => gotoStateAndSendMsg(msg, waitingHowAreYouResponse, "How are you?" )
  }

  def waitingHowAreYouResponse: Receive = {
    case msg: String => gotoStateAndSendMsg(msg, waitingByeResponse, "Bye")
  }

  def waitingByeResponse: Receive = {
    case msg: String => context.system.terminate()
  }

  private def gotoStateAndSendMsg(msgReceived:String, nextState: Actor.Receive, toSend:String): Unit = {
    if (!msgReceived.isEmpty) {
      println(s"Chatter: $msgReceived")
    }
    println(s"Asker:   $toSend")

    context.become(nextState)

    chatterRef ! toSend
  }

}
