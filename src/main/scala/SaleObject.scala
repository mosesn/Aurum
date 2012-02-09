case class SaleObject(val name: String, val sale: String, val sale_key: String, val store: String,
                      val description: Option[String], val sale_url: String, val begins: String,
                      val ends: Option[String], val image_urls: List[ImageObj],
                      val products: Option[List[String]])

object SaleObject {
  def apply(map: Map[String, Any]): SaleObject ={
    SaleObject((map("name")).toString,
               (map("sale")).toString,
               (map("sale_key")).toString,
               (map("store")).toString,
               if (map.contains("description")) Some(map("description").toString) else None,
               (map("sale_url")).toString,
               (map("begins")).toString,
               if (map.contains("ends")) Some(map("ends").toString) else None,
               getImages(map("image_urls").asInstanceOf[Map[String, List[Map[String, Any]]]]),
               if (map.contains("products")) Some(map("products").asInstanceOf[List[String]]) else None)
  }

  def getImages(map: Map[String, List[Map[String, Any]]]): List[ImageObj] = {
    (map.values.flatten map ({x: Map[String, Any] => makeImageObj(x) })).toList
  }

  def makeImageObj(x: Map[String, Any]) = ImageObj(x("url").toString, x("width").asInstanceOf[BigInt].toInt, x("height").asInstanceOf[BigInt].toInt)
}
