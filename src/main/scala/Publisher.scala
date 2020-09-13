import com.crankuptheamps.client.Client
import com.crankuptheamps.client.exception.AMPSException

object Publisher extends App {

  val client: Client = new Client("publisher")
  try{
    client.connect("tcp://127.0.0.1:9007/amps/json");
    client.logon();

    client.publish("messages", "{ \"message\" : \"Hello, world1!\" }")
  } catch {
    case e: AMPSException => println(e)
  } finally {
    client.close()
  }
}
