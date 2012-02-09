case class ImageObj(val url: String, val width: Int, val height: Int)

object ImageObj {
  def apply(x: Map[String, Any]): ImageObj =  ImageObj(x("url").toString, x("width").asInstanceOf[BigInt].toInt, x("height").asInstanceOf[BigInt].toInt)
}
