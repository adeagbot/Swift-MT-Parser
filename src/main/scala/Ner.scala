

package main.scala

object Ner extends App {

    protected object Option extends Enumeration {
      type Option =Value
      val A,
          F,
          K= Value
    } 
    val codeIndentifers = Map(
          "ARNU"   ->"Alien Registration Number",
          "CCPT"   -> "Passport Number",
          "CUST"   -> "Customer Identification Number",
          "DRLC"   -> "Driver's License Number",
          "EMPL"   -> "Employer Number", 
          "NIDN"   -> "National Identity Number",
          "SOSE"   -> "Social Security Number",
          "TXID"   -> "Tax Identification Number"
    ) 
    
    val  numberDetails = Map(
        1->"NAME",
        2->"ADDR",
        3->"CNTY",
        4->"DOB",
        5->"POB",
        6->"CUST",
        7->"NIDN", 
        8->"MISC"   
    ) 

// ARNU: Alien Registration Number
//CCPT: Passport Number
//CUST:Customer Identification Number.
//DRLC: Driver's License Number 
//EMPL: Employer Number 
//NIDN: National Identity Number
//SOSE: Social Security Number
//TXID: Tax Identification Number
//
//1:Name of the ordering customer
//2:Address Line(Address Line can be used to provide for example, street name and number, or building name)
//3:Country and Town(Town can be complemented by postal code (for example zip), country subdivision (for example state, province, or county).
//4:Date of Birth The number in the YYYYMMDD format.
//5:Place of Birth
//6:Customer Identification Number
//7:National Identity Number 
//8:Additional Information
  def entityExtraction={
    val multiline="""/1234567890123
I. Buy Buildings
200 Front Street
Toronto, Ontario
Canada"""
    val words=multiline.split("\n").map { x => x.trim.split(" ").toList}
    
    val test = List("questions", "tags", "users", "badges", "unanswered")
    
    val items=for (i <- 1 to test.length) yield test.sliding(i).toList
    //words.foreach { println }
    items.foreach { println }
    
  }
  //createBigFile
  entityExtraction  
  
}