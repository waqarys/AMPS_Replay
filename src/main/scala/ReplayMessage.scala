import com.crankuptheamps.client._
import com.crankuptheamps.client.exception.AMPSException

object ReplayMessage extends App {

  val client: Client = new Client("client-name")
  try{
    client.connect("tcp://127.0.0.1:9007/amps/json");
    client.logon();

    val command: Command =
      new Command("subscribe")
        .setTopic("messages-history")
        .setBookmark(Client.Bookmarks.EPOCH)
        //.setFilter("/details/items/description LIKE 'black.*scorpion'");

    val messageStream = client.execute(command).iterator()
    while (messageStream.hasNext){
      val m = messageStream.next()
      println("Received (" + m.getCommand() + ") : "+ m.getData())
    }
    /*messageStream.forEach{ m =>
      println("Received (" + m.getCommand() + ") : "+ m.getData())
    }*/
  } catch {
    case e: AMPSException => println(e)
  } finally {
    client.close()
  }
}
