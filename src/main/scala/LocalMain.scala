import java.io.File

import actors.Asker
import akka.actor.{ActorRef, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * Start the akka client.
  *
  */
object LocalMain extends App {

    val configFile = getClass.getClassLoader.getResource("asker_application.conf").getFile
    val config = ConfigFactory.parseFile(new File(configFile))
    val chatterUrl = config.getString("chatter")
    val system = ActorSystem("ClientSystem",config)
    val remoteActor = system.actorSelection(chatterUrl)

    val asker = system.actorOf(Props(classOf[Asker], remoteActor), name="local")

    asker ! "Start"

}
