package main.scala

import scala.util.Random
import java.text.SimpleDateFormat
import java.util.Calendar

object MT103 {
  val random = new Random
  val DATE_FORMAT = new SimpleDateFormat("yyMMdd") 
  val date = Calendar.getInstance()  
  
  def function103(i:(String,String,String,String),f50a:Array[String],
                  f50f:Array[String],
                  f50k:Array[String])
    :(String,String,String,Array[String]) = {
    if(i._2=="1"){
  		(i._1,i._2,i._3,Array("F01MIDLGB22AXXX0000000000"))		
  	}
  	else if(i._2=="2"){
  		(i._1,i._2,i._3,Array(i._1.split('-')(0)))
  	}
  	else if(i._2=="3"){
  		(i._1,i._2,i._3,Array(i._4))
  	}	
  	else if(i._2=="4"){
  		if( i._3=="20"){
  		(i._1,i._2,i._3,Array(
  		    (random.alphanumeric.take(7).mkString+random.nextInt(1000).toString).toUpperCase())
  		    )
  		}
  		else if(i._3=="23B"){
  			(i._1,i._2,i._3,Array("CRED"))
  		}
  		else if(i._3=="32A"){
  			date.add(Calendar.HOUR,+10)
  			val d = date.getTime().getTime()
  			val t = DATE_FORMAT.format(d).toString
  			val currency = t+"GBP"+random.nextInt(99999).toString.replaceAll("0","1")+",00"
  			(i._1,i._2,i._3,Array(currency))
  		}
  		else if(i._3=="50A"){
  			val l = f50a.length
  			(i._1,i._2,i._3,f50a(random.nextInt(l-1)).split(",").map(i=>i.trim()))
  		}
  		else if(i._3=="50K"){
  			val lk = f50k.length
  			(i._1,i._2,i._3,f50k(random.nextInt(lk-1)).split('|').map(i=>i.trim()))
  		}
  		else if(i._3=="50F"){
  			val lf = f50f.length
  			(i._1,i._2,i._3,f50f(random.nextInt(lf-1)).split('|').map(i=>i.trim()).take(4))
  		}
  		else if(i._3=="53D"){
  			(i._1,i._2,i._3,Array("GB"+random.nextInt(10000000).toString))
  		}
  		else if(i._3=="57A"){
  			(i._1,i._2,i._3,Array("BARCGB"+(10000+random.nextInt(1000)).toString.substring(1,3)+"XXX"))
  		}
  		else if(i._3=="59"){
  			(i._1,i._2,i._3,Array("/GB11VSOP55547873"+(100000+random.nextInt(1000)).toString.substring(2,5))) 
  		}
  		else if(i._3=="71A"){
  			if(random.nextInt(3)==0){
  				(i._1,i._2,i._3,Array("SHA"))
  			}else if(random.nextInt(3)==1){
  				(i._1,i._2,i._3,Array("OUR"))
  			}else{
  				(i._1,i._2,i._3,Array("BEN"))
  			}
  		}else{
  			(i._1,i._2,i._3,Array(""))
  		}
  	}
  	else{
  		(i._1,i._2,i._3,Array(""))
  	}
  } 
  
}