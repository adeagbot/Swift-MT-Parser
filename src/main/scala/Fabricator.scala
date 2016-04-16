package main.scala

import scala.util.Random
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.Calendar
import scala.io.Source
import java.io.File
import java.io.PrintWriter


object Fabricator extends App {

val DATE_FORMAT = new SimpleDateFormat("HHmmyyMMdd") 
val DATE_OUTPUT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") 

val samples=10


val date = Calendar.getInstance()
val idCode = Array("103","202")
//val timestamp: Long = System.currentTimeMillis / 1000
val time = date.getTime()
val timestamp = DATE_FORMAT.format(time)

val bicSeparator = Array("A", "X")
val field50 = Array("50A", "50F", "50K")
val region=Array("US","UK","HK")

//------------------- BLOCK --------------------
val mandatory = Array("1", "2", "4")
val optional = Array("3", "5")

val bicArray = Array("ICICIN33XXX", "ICICIN34XXX", "ICICIN35XXX", "ICICIN36XXX", "ICICIN37XXX",
    "ICICIN38XXX", "ICICIN39XXX", "ICICIN40XXX", "ICICIN41XXX", "ICICIN42XXX", "ICICIN43XXX",
    "ICICIN44XXX","ICICIN45XXX", "ICICIN46XXX", "ICICIN47XXX", "ICICIN48XXX", "ICICIN49XXX", 
    "ICICIN50XXX", "ICICIN51XXX","ICICIN52XXX", "ICICIN53XXX", "ICICIN54XXX", "ICICIN55XXX",
    "ICICIN56XXX", "ICICIN57XXX", "ICICIN58XXX","ICICIN59XXX", "ICICIN60XXX", "BARCUS33XXX", "BARCUS34XXX",
    "BARCUS35XXX", "BARCUS36XXX", "BARCUS37XXX","BARCUS38XXX", "BARCUS39XXX", "BARCUS40XXX", "BARCUS41XXX",
    "BARCUS42XXX", "BARCUS43XXX", "BARCUS44XXX","BARCUS45XXX", "BARCUS46XXX", "BARCUS47XXX", "BARCUS48XXX",
    "BARCUS49XXX", "BARCUS50XXX", "BARCUS51XXX","BARCUS52XXX", "BARCUS53XXX", "BARCUS54XXX", "BARCUS55XXX",
    "BARCUS56XXX", "BARCUS57XXX", "BARCUS58XXX","BARCUS59XXX", "BARCUS60XXX", "CITIAE33XXX", "CITIAE34XXX", 
    "CITIAE35XXX", "CITIAE36XXX", "CITIAE37XXX","CITIAE38XXX", "CITIAE39XXX", "CITIAE40XXX", "CITIAE41XXX",
    "CITIAE42XXX", "CITIAE43XXX", "CITIAE44XXX", "CITIAE45XXX", "CITIAE46XXX", "CITIAE47XXX", "CITIAE48XXX",
    "CITIAE49XXX", "CITIAE50XXX", "CITIAE51XXX", "CITIAE52XXX", "CITIAE53XXX", "CITIAE54XXX", "CITIAE55XXX",
    "CITIAE56XXX", "CITIAE57XXX", "CITIAE58XXX", "CITIAE59XXX", "CITIAE60XXX", "DEUTDE33XXX", "DEUTDE34XXX",
    "DEUTDE35XXX", "DEUTDE36XXX", "DEUTDE37XXX", "DEUTDE38XXX", "DEUTDE39XXX", "DEUTDE40XXX", "DEUTDE41XXX",
    "DEUTDE42XXX", "DEUTDE43XXX", "DEUTDE44XXX", "DEUTDE45XXX", "DEUTDE46XXX", "DEUTDE47XXX", "DEUTDE48XXX",
    "DEUTDE49XXX", "DEUTDE50XXX", "DEUTDE51XXX", "DEUTDE52XXX", "DEUTDE53XXX", "DEUTDE54XXX", "DEUTDE55XXX",
    "DEUTDE56XXX", "DEUTDE57XXX", "DEUTDE58XXX", "DEUTDE59XXX", "DEUTDE60XXX", "LLODFR33XXX", "LLODFR34XXX",
    "LLODFR35XXX", "LLODFR36XXX", "LLODFR37XXX", "LLODFR38XXX", "LLODFR39XXX", "LLODFR40XXX", "LLODFR41XXX",
    "LLODFR42XXX", "LLODFR43XXX", "LLODFR44XXX", "LLODFR45XXX", "LLODFR46XXX", "LLODFR47XXX", "LLODFR48XXX",
    "LLODFR49XXX", "LLODFR50XXX", "LLODFR51XXX", "LLODFR52XXX", "LLODFR53XXX", "LLODFR54XXX", "LLODFR55XXX",
    "LLODFR56XXX", "LLODFR57XXX", "LLODFR58XXX", "LLODFR59XXX", "LLODFR60XXX", "BARCFR33XXX", "BARCFR34XXX", 
    "BARCFR35XXX", "BARCFR36XXX", "BARCFR37XXX", "BARCFR38XXX", "BARCFR39XXX", "BARCFR40XXX", "BARCFR41XXX", 
    "BARCFR42XXX", "BARCFR43XXX", "BARCFR44XXX", "BARCFR45XXX", "BARCFR46XXX", "BARCFR47XXX", "BARCFR48XXX",
    "BARCFR49XXX", "BARCFR50XXX", "BARCFR51XXX", "BARCFR52XXX", "BARCFR53XXX", "BARCFR54XXX", "BARCFR55XXX",
    "BARCFR56XXX", "BARCFR57XXX", "BARCFR58XXX", "BARCFR59XXX", "BARCFR60XXX", "HSBCHK33XXX", "HSBCHK34XXX",
    "HSBCHK35XXX", "HSBCHK36XXX", "HSBCHK37XXX", "HSBCHK38XXX", "HSBCHK39XXX", "HSBCHK40XXX", "HSBCHK41XXX",
    "HSBCHK42XXX", "HSBCHK43XXX", "HSBCHK44XXX", "HSBCHK45XXX", "HSBCHK46XXX", "HSBCHK47XXX", "HSBCHK48XXX",
    "HSBCHK49XXX", "HSBCHK50XXX", "HSBCHK51XXX", "HSBCHK52XXX", "HSBCHK53XXX", "HSBCHK54XXX", "HSBCHK55XXX",
    "HSBCHK56XXX", "HSBCHK57XXX", "HSBCHK58XXX", "HSBCHK59XXX", "HSBCHK60XXX", "CHASUS33XXX", "CHASUS34XXX",
    "CHASUS35XXX", "CHASUS36XXX", "CHASUS37XXX", "CHASUS38XXX", "CHASUS39XXX", "CHASUS40XXX", "CHASUS41XXX",
    "CHASUS42XXX", "CHASUS43XXX", "CHASUS44XXX", "CHASUS45XXX", "CHASUS46XXX", "CHASUS47XXX", "CHASUS48XXX",
    "CHASUS49XXX", "CHASUS50XXX", "CHASUS51XXX", "CHASUS52XXX", "CHASUS53XXX", "CHASUS54XXX", "CHASUS55XXX",
    "CHASUS56XXX", "CHASUS57XXX", "CHASUS58XXX", "CHASUS59XXX", "CHASUS60XXX")
    
val random = new scala.util.Random
val path = System.getProperty("user.dir")+
                File.separator+"src"+
                File.separator+"main"+
                File.separator+"resources"+
                File.separator;  

//OUTGOING
val idOutTuple = (1 to samples).map(i => {
    date.add(Calendar.HOUR,+i)
	  date.getTime().getTime()
}).flatMap(x => {
	val bufferId = new scala.collection.mutable.ArrayBuffer[(String,String,String,String,String,String)]
	val timestamp = DATE_FORMAT.format(x).toString
	bicArray.foreach(y => {
		var timestamp2 = DATE_FORMAT.format(x+1000L).toString
		bufferId += (
		(
		   "O",idCode(0),timestamp, y.replaceAll("XXX",bicSeparator(0)+"XXX"),
		   Stream.continually(random.nextInt(1000)).take(10).mkString,
		    timestamp2
		),
		(
		    "O",idCode(1),timestamp, y.replaceAll("XXX",bicSeparator(1)+"XXX"),
		    Stream.continually(random.nextInt(1000)).take(10).mkString,
		    timestamp2
		 )
		)
	})
	bufferId.toList
	})

	//INCOMING
val idInTuple = (1 to samples).map(i => {
    date.add(Calendar.HOUR,+i)
	date.getTime().getTime()
}).flatMap(x => {
	val bufferId = new scala.collection.mutable.ArrayBuffer[(String,String,String,String)]
	val timestamp = DATE_FORMAT.format(x).toString
	bicArray.foreach(y => {
		bufferId += (
		("I",idCode(0),timestamp, y.replaceAll("XXX",bicSeparator(0)+"XXX")),
		("I",idCode(1),timestamp, y.replaceAll("XXX",bicSeparator(1)+"XXX"))
		)
	})
	bufferId.toList
	})

val idOut = idOutTuple.map( i => {
  i._1+i._2+i._3+i._4+i._5+i._6+"N-"+region(random.nextInt(3))
  } 
)
val idIn = idInTuple.map( i => {
  i._1+i._2+i._3+i._4+"N-"+region(random.nextInt(3))
  } 
)
val id = idOut ++ idIn

val block = id.flatMap(x => {
	val buffer = new scala.collection.mutable.ArrayBuffer[(String,String)]
	mandatory.foreach(y => {
		buffer += ((x, y))
	})
	val optblock1 = Random.shuffle(optional.toList).head
	val optblock2 = Random.shuffle(optional.toList).head
	if( optblock1 == "3" || optblock1 == "5"){
		buffer += ((x, optblock1))
		if(optblock1=="5"){
			buffer += ((x, "S")) 
		}
	}
	if (optblock1 != optblock2 ){
		buffer += ((x, optblock2))
		if(optblock2=="5"){
			buffer += ((x, "S")) 
		}
	}
	buffer.toList
}) 

val fileName = path+"ref.txt"
val refData = Source.fromFile(fileName).getLines().map(line => line.split(",")).toArray

val block202_4 = block.filter(i=>{i._1.substring(1,4).toUpperCase=="202" && i._2=="4"}).flatMap( x => {
	val arr = new scala.collection.mutable.ArrayBuffer[(String,String,String,String)]
	refData.filter(i=>{i(0)=="202" && i(2)=="M"}).foreach( i => {
		arr += ((x._1,x._2,i(1),""))
	}
	)
	arr += ((x._1,x._2,field50(random.nextInt(3)),""))
	refData.filter(i => {i(0)=="202" && i(2)=="O" && random.nextInt(2)==1}).foreach{ i => {
		if(x._2=="4"){
			arr += ((x._1,x._2,i(1),""))
		}
	}}
	arr.toList
})

val block103_4 = block.filter(i=>{i._1.substring(1,4).toUpperCase=="103" && i._2=="4"}).flatMap( x => {
	val arr = new scala.collection.mutable.ArrayBuffer[(String,String,String,String)]
	refData.filter(i=>{i(0)=="103" && i(2)=="M"}).foreach( i => {
		arr += ((x._1,x._2,i(1),""))
	}
	)
	arr += ((x._1,x._2,field50(random.nextInt(3)),""))
	refData.filter(i => {i(0)=="202" && i(2)=="O" && random.nextInt(2)==1}).foreach{ i => {
		if(x._2=="4"){
			arr += ((x._1,x._2,i(1),""))
		}
	}}
	arr.toList
})

val block202_not4 = block.filter(i=>{i._1.substring(1,4).toUpperCase=="202" && i._2!="4"}).map({
	i=>{
		if(i._2=="3"){
			if(random.nextInt(2)==1){
				(i._1,i._2,"119","COV")
			}else{
				(i._1,i._2,"","")
			}
		}else{
			(i._1,i._2,"","")
		}
	}
})

val block103_not4 = block.filter(i=>{i._1.substring(1,4).toUpperCase=="103" && i._2!="4"}).map({
	i=>{
		if(i._2=="3"){
			if(random.nextInt(2)==1){
				(i._1,i._2,"119","STP")
			}else{
				(i._1,i._2,"","")
			}
		}else{
			(i._1,i._2,"","")
		}
	}
})


val tot = block202_4 ++ block103_4 ++ block202_not4 ++ block103_not4


def add50a50f50k(list:Array[(String,String,String,String)])
  :Array[(String,String,String,String,String,String,String,Array[String])]={
  
  val filename1 = path+"50A.txt"
  val f50a = Source.fromFile(filename1)("UTF-8").getLines().toArray
  val filename2 = path+"50F.txt"
  val f50f = Source.fromFile(filename2)("UTF-8").getLines().toArray    
  val filename3 = path+"50K.txt"
  val f50k = Source.fromFile(filename3)("UTF-8").getLines().toArray
  
  list.map(i => {
  	if(i._1.substring(1,4).toUpperCase=="103"){
  		MT103.function103(i,f50a,f50f,f50k)
  	}else{
  		MT202.function202(i,f50a,f50f,f50k)
  	}
  }).map(i=> {    
    val s=i._1.split('-')
  	(s(0),
  	    time.getTime().toString(),
  	    i._1.substring(0,1).toUpperCase,
  	    i._1.substring(1,4).toUpperCase,
  	    s(1),
  	    i._2,
  	    i._3,
  	    i._4)
  }).toArray  
}

// val timestamp = System.currentTimeMillis().toString

def uuid = java.util.UUID.randomUUID.toString

val  n=add50a50f50k(tot.toArray)

val uuid_lookup=n.map(_._1).distinct.map(i=>(i,uuid)).toMap;

val writer = new PrintWriter(path+"mt_message.txt", "UTF-8");

val mt=n.map(x=>(uuid_lookup.get(x._1).get,x._2,x._3,x._4,x._5,x._6,x._7,x._8))

val one=mt.filter(i=>i._4=="103" && i._7=="50A").take(1)

mt.foreach(
    x=>writer.println(x._1+"|"+DATE_OUTPUT_FORMAT.format(new java.util.Date(x._2.toLong))+"|"+x._3+"|"+x._4+"|"+x._5+"|"+x._6+"|"+x._7+"|"+x._8.mkString(";"))
)


mt.filter(_._1==one(0)._1)
 .sortBy(_._6)
 .foreach(x=>println(x._1+"|"+DATE_OUTPUT_FORMAT.format(new java.util.Date(x._2.toLong))+"|"+x._3+"|"+x._4+"|"+x._5+"|"+x._6+"|"+x._7+"|"+x._8.mkString(";")))

writer.close

}


