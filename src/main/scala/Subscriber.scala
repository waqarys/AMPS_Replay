import com.crankuptheamps.client.{Client, Message}
import com.crankuptheamps.client.exception.AMPSException

object Subscriber extends App {

  val client: Client = new Client("subscriber")
  try{
    client.connect("tcp://127.0.0.1:9007/amps/json");
    client.logon();

    val messageStream = client.subscribe("messages").iterator()
    while (messageStream.hasNext){
      val m = messageStream.next()
      println(m.getData)
    }
  } catch {
    case e: AMPSException => println(e)
  } finally {
    client.close()
  }
}
