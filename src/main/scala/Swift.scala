
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
  
  val filename = System.getProperty("user.dir")+
                File.separator+"src"+
                File.separator+"main"+
                File.separator+"resources"+
                File.separator+"MT101.txt";  
  
  val list=Parser.structuredItems(Parser.messageItems(Source.fromFile(filename)))
  list.filter(!_._3.isEmpty())
  .map(i=>(i._1,i._2,"",i._3))
  .flatMap(i=>{
    val a=i._4.split("\r")
    val length=a.length;
    if(length>1){
       val array = ArrayBuffer[(String,String,String,String)]()
       a.zipWithIndex.foreach{ case(x,index) => {
           val line=index+1
           array.append((i._1,i._2,line.toString(),a(index)))
         }
       }
       array.toList
    }else{
      Array((i._1,i._2,i._3,i._4)).toList
    }    
  })
  .map(i=>i._1 +"|"+i._2+"|"+i._3+"|"+i._4)
  .foreach(println)
}
