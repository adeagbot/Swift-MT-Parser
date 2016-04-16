
package main.scala
/**
 * @author terry
 */

import java.io.File

import scala.io.Source
import java.io.FileNotFoundException
import java.io.IOException

object Swift extends App{
  
  //val file=this.args(0);
  val filename = System.getProperty("user.dir")+
                File.separator+"src"+
                File.separator+"main"+
                File.separator+"resources"+
                File.separator+"example.txt";  

  val source=Source.fromFile(filename) 
  val message=source.mkString.split("\n")  
  //Parser.messageItems(message)
  Parser.structuredItems(Parser.messageItems(message))
  .foreach(println)
  source.close;  
}
