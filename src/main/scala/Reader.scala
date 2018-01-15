import java.io.File
import java.io.PrintWriter
import akka.actor._
import scala.io.Source

class countWords extends Actor{
  def receive={
    case msg:Int=>println("Count is "+msg)
    case s1:String=>{
      val counts=s1.split("\\s+").groupBy(x=>x).mapValues(x=>x.length)
      println("Count is "+counts)
      println("Count of word File is "+counts("this"))

      val words=s1.split(" ")
      println(words.length)


      val writer = new PrintWriter(new File("src/main/resources/Write.txt"))
      writer.write(""+counts)
      writer.close()

    }
    case _=>println("NOTHING FOUND")
  }

}

object Reader {

  def main(args:Array[String]): Unit ={
    println(Source.fromFile("src/main/resources/File1.txt"))
    val s1=Source.fromFile("src/main/resources/File1.txt").mkString //Read entire file
    println(s1)

    val actorSystem=ActorSystem("ActorSystem")
    val actor=actorSystem.actorOf(Props[countWords],"actor")
    actor!s1

actorSystem.terminate()
  }


}
