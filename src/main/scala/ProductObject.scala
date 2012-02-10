case class ProductObject(val name: String, val product: String, val id: Number, val brand: String,
                         val url: String, val image_urls: List[ImageObj], val skus: List[SKUObj],
                         val description: Option[String], val fit_notes: Option[String], val material: Option[String],  //contents
			 val care_instructions: Option[String], val origin: Option[String]) //contents


object ProductObject {
	def apply(map: Map[String, Any]): ProductObject ={
	          ProductObject((map("name")).toString,
			        (map("product")).toString,
				(map("id")).asInstanceOf[Number],
				(map("brand")).toString,
				(map("url")).toString,
				getImages(map("image_urls").asInstanceOf[Map[String, List[Map[String, Any]]]]),
				getSKUs(map("skus").asInstanceOf[Map[String, List[Map[String, Any]]]]),
				if (map.contains("description")) Some(map("description").toString) else None,
				if (map.contains("fit_notes")) Some(map("fit_notes").toString) else None,
				if (map.contains("material")) Some(map("material").toString) else None,
				if (map.contains("care_instructions")) Some(map("care_instructions").toString) else None,
				if (map.contains("origin")) Some(map("origin").toString) else None)
	}

	//copied and pasted from SaleObject :(
	def getImages(map: Map[String, List[Map[String, Any]]]): List[ImageObj] = {
    (map.values.flatten map ({x: Map[String, Any] => ImageObj(x) })).toList
  }

	def getSKUs(map: Map[String, List[Map[String, Any]]]): List[SKUObj] = {
    (map.values.flatten map ({x: Map[String, Any] => SKUObj(x) })).toList
  }

}
