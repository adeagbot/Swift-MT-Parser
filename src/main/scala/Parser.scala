
package main.scala

/**
 * @author terry
 */

import scala.collection.mutable.ArrayBuffer


//import java.util.Base64 //only in java 8
import java.nio.charset.StandardCharsets
import javax.xml.bind.DatatypeConverter

//def encodeJava8(text:String)={
//val message = text.getBytes(StandardCharsets.UTF_8);
//val encoded = Base64.getEncoder().encodeToString(message);
//val decoded = Base64.getDecoder().decode(encoded);
//
//println(encoded);
//println(new String(decoded, StandardCharsets.UTF_8));  
//}


//    Message items found in ``readable`` as tuples of the form ``(nestingLevel, type, value)`` with:
//    * nestingLevel: level of nested blocks
//    * type: one of: 'message', 'block', 'field', 'value'
//    * value: for 'block' this is the block key, for 'field' this is the field name, 'value' this is the value
//      and for 'message' this is ``None``.
//    The messages have to be stored in EDIFACT / ISO 15022 format.

object Parser {
  def uuid = java.util.UUID.randomUUID.toString
  def encode(text:String)=DatatypeConverter.printBase64Binary(text.getBytes(StandardCharsets.UTF_8))
  
  def decode(text:String)=new String(DatatypeConverter.parseBase64Binary(text),StandardCharsets.UTF_8)    
  
  def messageItems(message:Array[String]):Array[(Int,String,String)] = {
        var state =State.BEFORESTARTOFBLOCK
        var text = ""
        var blockKey = ""
        var fieldKey = ""
        var level = 0        
        val list = ArrayBuffer[(Int,String,String)]()
        for {
          line<-message 
          c <- line+"\n"
        } {
           state match {
             case State.BEFORESTARTOFBLOCK =>{
                if (c == '{'){//_InBlockKey
                  state = State.INBLOCKKEY
                  level += 1
                  text = ""             
                }else if(c=='}'){
                   if(level==0){
                     //raise Error('unmatched %r outside of any block must be removed' % char)
                   }else{
                     level -= 1
                   } 
                }else if (c == '\n'){
                   if(level!=0){
                     //raise Error(u'nested block must be closed (state=%r, level=%d)' % (state, level))
                   }else  {
                     //list+=((level, "message", ""))
                   }
                }else if (c != '\r'){
                  //raise Error('block must start with %r instead of %r' % ('{', char))          
                }               
             }
             case State.INBLOCKKEY=>{
                if (c == ':'){//InLine
                    state = State.INLINE
                    blockKey =text
                    list+=((level, "block", blockKey)) 
                    text = ""
                }else if (c.isLetterOrDigit){
                  text += c
                }else{
                  //raise Error('block id must consist of decimal digits bug encountered %r' % char)          
                }                 
             }
             case State.INLINE=>{
               if (c == '{'){//_InBlockKey
                    state = State.INBLOCKKEY 
                    level += 1
                    text = ""
               }else if (c == '}'){//BeforeStartOfBlock
                    state = State.BEFORESTARTOFBLOCK
                    level -= 1
                    //assert level >= 0
               }else if (c == ':'){//InFieldKey
                    state =State.INFIELDKEY
                    text = ""
               }else if (c == '\n'){
                // list+=((level,"value", ""))  
               }else if (c!=' ' && c!='\t'){//InValue 
                    state = State.INVALUE
                    text = c+""
               }               
             }
             case State.INFIELDKEY=>{
                if (c == ':'){//InFieldValue
                    state = State.INFIELDVALUE
                    fieldKey = text
                    text = ""
                }else if (c.isLetterOrDigit){
                    text += c  
                }                  
             }
             case State.INFIELDVALUE=>{
              if (c == '\n' || c == '}' ||c== '\r'){ 
                  list+=((level, "field", fieldKey))  
                  list+=((level, "value", text))    
                  fieldKey = ""
                  text =""
                  if (c == '}'){//BeforeStartOfBlock
                      state =State.BEFORESTARTOFBLOCK 
                      level -= 1
                      //assert level >= 0                  
                  }else{//InLine
                      state =State.INLINE     
                  }             
              } else{
                text += c  
              }                 
             }
             case State.INVALUE=>{
                if (c == '\n' || c == '}' ||c== '\r'){
                  list+=((level, "value", text))    
                    text =""
                    if (c == '}'){//BeforeStartOfBlock
                        state =State.BEFORESTARTOFBLOCK
                        level -= 1 
                        //assert level >= 0                  
                    }else{//InLine
                        state =State.INLINE     
                    }             
                } else{
                  text += c  
                }                 
             }
             case _=>{
               
             }
           }
        }
        list.toArray
    }
  
  
 def structuredItems(list:Array[(Int,String,String)]):Array[(String,String,String)]={
    var block = ""
    var field = ""
    var valueSoFar = "" 
    val multiLines = ArrayBuffer[String]();
    val array = ArrayBuffer[(String,String,String)]()
    for {
      (level,kind,value)  <-list  
     }{
       kind match {
         case "block"=>{
             if (level == 1) {
                if(!block.isEmpty ) {
                  if(multiLines.length==1){
                    array += ((block, field, valueSoFar))
                  }else{
                    array += ((block, field, multiLines.mkString(";")))
                  }                  
                }
                block = value
                field=""
                valueSoFar=""
                multiLines.clear()
            } else {
              field = value
            }            
         }
         case "field"=>{            
            if(!field.isEmpty && !valueSoFar.isEmpty) {
                if(multiLines.length==1){
                  array += ((block, field, valueSoFar))
                }else{
                  array += ((block, field, multiLines.mkString(";")))
                }
            }
            multiLines.clear()
            valueSoFar = ""
            field=value
         }
         case "value"=>{
             multiLines+=value
             valueSoFar+=value
         }
         case _=>{
            //assert False, u'kind=%r' % kind
         }           
       }
   }
   // add the last item.
    if(multiLines.length==1){
      array += ((block, field, valueSoFar))
    }else{
      array += ((block, field, multiLines.mkString(";")))
    }           
    array.toArray
 }
  
}