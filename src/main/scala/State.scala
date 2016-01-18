package main.scala

/**
 * @author terry
 */
object State extends Enumeration {
  type State =Value
  val BEFORESTARTOFBLOCK,
      INBLOCKKEY,
      INLINE,
      INFIELDKEY,
      INFIELDVALUE,
      INVALUE = Value
}