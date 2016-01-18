package main.scala

/**
 * @author terry
 */
class XMLWriter {
    val EOL="\n"
    def startMessage="<message>"
    def endMessage=EOL+"</message>"
    def startBlock1=EOL+"<block1>"
    def startBlock2=EOL+"<block2>"
    def startBlock3 =EOL+"<block3>"
    def startBlock4 =EOL+"<block4>"
    def startBlock5 =EOL+"<block5>"
    def startBlockS =EOL+"<blockS>"
    def endBlock1=EOL+"</block1>"
    def endBlock2=EOL+"</block1>"
    def endBlock3=EOL+"</block1>"
    def endBlock4=EOL+"</block1>"
    def endBlock5=EOL+"</block5>"
    def endBlockS=EOL+"</blockS>"
    def appendField(field:(String,String)):String={
      EOL+"\t<field"+field._1+">"+field._2+"<field"+field._1+"\\>"
    }
}