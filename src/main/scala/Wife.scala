
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
import net.sourceforge.wife.swift.SwiftParser2;
import net.sourceforge.wife.swift.model.SwiftBlock;
import net.sourceforge.wife.swift.model.SwiftMessage;
import net.sourceforge.wife.swift.model.Tag;
import scala.collection.JavaConversions._

import org.apache.log4j.Logger
import org.apache.log4j.PropertyConfigurator
import net.sourceforge.wife.swift.SwiftParser2

object Wife extends App{
 
  //val file=this.args(0);
  Logger.getLogger("SwiftParser2")
  //PropertiesConfigurator is used to configure logger from properties file
  PropertyConfigurator.configure("log4j.properties");
  
  val filename = System.getProperty("user.dir")+
                File.separator+"src"+
                File.separator+"main"+
                File.separator+"resources"+
                File.separator+"MT103.txt";
  
 val p = new SwiftParser2(Source.fromFile(filename).reader());
 val m = p.message();
 for (i<-1 to 5){
   val b=m.getBlock(i)
   for(i<- 0 to b.getTagCount-1){
     val t=b.getTag(i)
     println(b.getId+","+t.getName +" , "+t.getValue)
   }
 }
 
}
