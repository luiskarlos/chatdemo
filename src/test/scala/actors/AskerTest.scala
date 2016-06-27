package actors

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit, TestProbe}
import org.scalamock.scalatest.MockFactory
import org.scalatest.{MustMatchers, WordSpecLike}

/**
  *
  */
class AskerTest(testSystem: ActorSystem)
  extends TestKit(testSystem)
    with ImplicitSender
    with WordSpecLike
    with MustMatchers
    with MockFactory {

    def this() = this(ActorSystem("ChatterTest"))

    "Asker actor" must {

      "send messages in order" in {
        val probe = TestProbe()
        val echo = system.actorOf(TestActors.echoActorProps)
        val actorSelection = system.actorSelection(probe.ref.path)

        val asker = system.actorOf(Props(classOf[Asker], actorSelection))

        asker ! "Start"

        probe.expectMsg("Hi")
        probe.forward(echo)
        probe.expectMsg("How are you?")
        probe.forward(echo)
        probe.expectMsg("Bye")
      }

    }
  }
