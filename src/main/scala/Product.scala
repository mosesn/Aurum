package org.aurum

case class Product(val name: String, val product: String, val id: Number, val brand: String,
                         val url: String, val image_urls: List[Image], val skus: List[SKU],
                         val description: Option[String], val fit_notes: Option[String], val material: Option[String],
			 val care_instructions: Option[String], val origin: Option[String])


object Product {
  def apply(map: Map[String, Any]): Product ={
    Product((map("name")).toString,
		  (map("product")).toString,
		  (map("id")).asInstanceOf[Number],
		  (map("brand")).toString,
		  (map("url")).toString,
		  getImages(map("image_urls").asInstanceOf[Map[String, List[Map[String, Any]]]]),
		  getSKUs(map("skus").asInstanceOf[List[Map[String, Any]]]),
		  if (map.contains("description")) Some(map("description").toString) else None,
		  if (map.contains("fit_notes")) Some(map("fit_notes").toString) else None,
		  if (map.contains("material")) Some(map("material").toString) else None,
		  if (map.contains("care_instructions")) Some(map("care_instructions").toString) else None,
		  if (map.contains("origin")) Some(map("origin").toString) else None)
  }

  def getImages(map: Map[String, List[Map[String, Any]]]): List[Image] = {
    (map.values.flatten map ({x: Map[String, Any] => Image(x) })).toList
  }

  def getSKUs(lst: List[Map[String, Any]]): List[SKU] = {
    lst map ({x: Map[String, Any] => SKU(x) })
  }

}
