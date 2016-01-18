package main.scala

/**
 * @author terry
 */

import scala.collection.mutable.ArrayBuffer
import scala.io.Source


//    Message items found in ``readable`` as tuples of the form ``(nestingLevel, type, value)`` with:
//    * nestingLevel: level of nested blocks
//    * type: one of: 'message', 'block', 'field', 'value'
//    * value: for 'block' this is the block key, for 'field' this is the field name, 'value' this is the value
//      and for 'message' this is ``None``.
//    The messages have to be stored in EDIFACT / ISO 15022 format.

object Parser {
  def messageItems(source:io.Source):Array[(Int,String,String)] = {
        var state =State.BEFORESTARTOFBLOCK
        var text = ""
        var blockKey = ""
        var fieldKey = ""
        var level = 0        
        val list = ArrayBuffer[(Int,String,String)]()
        for {
//        line<-source.getLines  
//        c <- line
          c<- source.mkString
        } {
          if(state==State.BEFORESTARTOFBLOCK){//BeforeStartOfBlock
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
                 }else  
                   list+=((level, "message", ""))
              }else if (c != '\r'){
                //raise Error('block must start with %r instead of %r' % ('{', char))          
              }
          }else if (state==State.INBLOCKKEY){//InBlockKey
              if (c == ':'){//InLine
                  state = State.INLINE
                  blockKey =text
                  list+=((level, "block", blockKey)) 
                  text = ""
              }else if (c.isLetterOrDigit){
              //}else if (c.isDigit){// is digit only should be used
                text += c
              }else{
                //raise Error('block id must consist of decimal digits bug encountered %r' % char)          
              }  
          }else if (state==State.INLINE){//InLine
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
               list+=((level,"value", ""))  
             }else{//InValue
                  state = State.INVALUE
                  text = c+""
             }
          }else if (state==State.INFIELDKEY){//InFieldKey
              if (c == ':'){//InFieldValue
                  state = State.INFIELDVALUE
                  fieldKey = text
                  text = ""
              }else{
                  text += c  
              }         
          }else if (state==State.INFIELDVALUE){//InFieldValue
              if (c == '\n' || c == '}'){ 
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
          }else if(state==State.INVALUE){//InValue:
              if (c == '\n' || c == '}'){
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
        }
        list.toArray
    }
  
   def structuredItems(list:Array[(Int,String,String)]):Array[(String,String,String)]={
      var block = ""
      var field = ""
      var valuesSoFar = "" 
      var parent=""
      val array = ArrayBuffer[(String,String,String)]()
      for {
       (level,kind,value)  <-list  
       }{
         if(kind=="block"){
           if(!block.isEmpty()){
             if(level==1 && block=="1" || block=="2"){
               array+=(( block, field, valuesSoFar))
             } else{
               if(field.isEmpty())
                 array+=((parent , block, valuesSoFar))
               else 
                 array+=((parent , field, valuesSoFar))
             }
           }
           if(level==1)parent=value 
           block = value
           field = ""
           valuesSoFar = ""
         }else if(kind=="field"){
            
            if(block.isEmpty()){//TO DO 
              //raise Error(u'block for field "%s" must be specified' % value)
            }else{              
              if(!field.isEmpty()){
                  array+=((parent, field, valuesSoFar))
              }
              field = value
              valuesSoFar = ""                
            }
         }else if(kind=="value"){
            valuesSoFar+=value
         }else{
           //assert False, u'kind=%r' % kind
         }
     }
     // # Yield the last item.
     if(valuesSoFar.length>0)array+=(( parent, block, valuesSoFar))       
     array.toArray
   }  
}