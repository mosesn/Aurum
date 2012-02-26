package org.aurum

case class Image(val url: String, val width: Int, val height: Int)

object Image {
  def apply(x: Map[String, Any]): Image =  Image(x("url").toString, x("width").asInstanceOf[BigInt].toInt, x("height").asInstanceOf[BigInt].toInt)
}
