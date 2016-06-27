import java.io.File

import actors.Chatter
import akka.actor._
import com.typesafe.config.ConfigFactory
import modules.Dialogue

/**
  * Start the akka server.
  */
object RemoteMain extends App {

    val configFile = getClass.getClassLoader.getResource("chatter_application.conf").getFile
    val config = ConfigFactory.parseFile(new File(configFile))
    val system = ActorSystem("RemoteSystem" , config)

    system.actorOf(Props(classOf[Chatter], Dialogue()), name="chatter")

    println("Chatter ready")

}


