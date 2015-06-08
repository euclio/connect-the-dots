package com.acrussell.connectdots

object Main extends App {
  val result = DigitClassifier.recognize(args(0))
  println(result)
}
