package actors

import modules.Dialogue
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{MustMatchers, WordSpecLike}
import org.scalamock.scalatest.MockFactory

class ChatterTest(testSystem: ActorSystem)
  extends TestKit(testSystem)
  with ImplicitSender
  with WordSpecLike
  with MustMatchers
  with MockFactory {


  /**
    * Helper class to Initialize the Model empty
    */
  class MockableDialogue extends Dialogue(Map.empty, (Int) => 0)

  def this() = this(ActorSystem("ChatterTest"))

  "Chatter actor" must {

    "sent the response from model.Dialogue to the sender" in {

      val dialogue = mock[MockableDialogue]
      (dialogue.msg _).expects("Hi").returning(Some("Yo, what's up"))

      val actorRef = system.actorOf(Props(classOf[Chatter], dialogue))

      actorRef ! "Hi"

      expectMsg("Yo, what's up")
    }
  }
}
