import com.crankuptheamps.client.Client
import com.crankuptheamps.client.exception.AMPSException

object RecordMessage extends App {

  val client: Client = new Client("client-name")
  try{
    client.connect("tcp://127.0.0.1:9007/amps/json");
    client.logon();

    client.publish("messages-history", "{ \"message\" : \"Hello, world!\" }")
  } catch {
    case e: AMPSException => println(e)
  } finally {
    client.close()
  }
}
