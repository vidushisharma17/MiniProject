import java.io.File
import java.io.PrintWriter
import akka.actor._
import scala.io.Source
import akka.routing.{ ActorRefRoutee, RoundRobinRoutingLogic, Router }
import akka.routing.RoundRobinPool

class WordCounting extends Actor{
  def receive={
    case msg:Int=>println("Count is "+msg)
    case text:String=>{
      val s1=Source.fromFile("src/main/resources/"+text).mkString //Read entire file
     // println(s1)
      val counts=s1.split("\\s+").groupBy(x=>x).mapValues(x=>x.length)
      val writer = new PrintWriter(new File("src/main/resources/Write"+text))
      writer.write(""+counts)
      writer.close()
      Thread.sleep(1000)
      println("Done writing to file: Write" + text)

    }
    case _=>println("NOTHING FOUND")
  }

}

object FinalReader {

  def main(args:Array[String]): Unit ={

    val s1=Source.fromFile("src/main/resources/FileList.txt").getLines()
    val actorSystem=ActorSystem("ActorSystem")
  //val actor=actorSystem.actorOf(Props[WordCounting],"actor")//Read entire file

    val router2: ActorRef =actorSystem.actorOf(RoundRobinPool(3).props(Props[WordCounting]), "router2")

    Source.fromFile("src/main/resources/FileList.txt").getLines.foreach
    {
      x => println(x)

      router2!x
    };

  }



}
