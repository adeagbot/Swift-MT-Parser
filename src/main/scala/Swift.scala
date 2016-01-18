
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
                File.separator+"MT101.txt";  
  
  val list=Parser.structuredItems(Parser.messageItems(Source.fromFile(filename)))
  list.foreach(println) 
   
//   def fold(list:Array[(Int,String,String,String)])
//   //:Array[(Int,String,String,String)]
//   ={
//     val array = ArrayBuffer[(Int,String,String,String,Map[String,String])]()
//     for (i<-0 to list.length-1 ){
//       val map=Map.empty[String,String]
//       if(list(i)._4.isEmpty()){    
//         //val map=Map.empty[String,String]
//          for (j<-i+1 to list.length-1){
//            if(list(i)._1!=list(j)._1){
//             map+=(list(j)._3->list(j)._4)
//            }
//          }
//          for ((k,v) <- map){
//            println(k+" :"+v)
//          }
//          println("end")
//          map.empty
//       }else {
//         //
//         //array+=(list(i)._1,list(i)._2,list(i)._3,list(i)._4,map)
//       }
//     }     
//     array.toArray     
//   }
}
