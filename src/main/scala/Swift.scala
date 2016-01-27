
package main.scala
/**
 * @author terry
 */

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.collection.mutable.Map

object Swift extends App{
  
  //val file=this.args(0);
  val filename = System.getProperty("user.dir")+
                File.separator+"src"+
                File.separator+"main"+
                File.separator+"resources"+
                File.separator+"example.txt";  //FATFtestdata.txt
  
  val list=Parser.structuredItems(Parser.messageItems(Source.fromFile(filename)))
  list.filter(!_._3.isEmpty())
  .map(i=>(i._1,i._2,i._3))
  .map(i=>{
    println(i._3.length)
    val a=i._3.split("\n").mkString("|")
    i._1 +"|"+i._2+"|"+a
    
  })
  .foreach(println)
  
}
