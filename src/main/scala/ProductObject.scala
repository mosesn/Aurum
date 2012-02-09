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
									(map("image_urls")).asInstanceOf[List[ImageObj]],
									(map("skus")).asInstanceOf[List[SKUObj]],
									if (map.contains("description")) Some(map("description").toString) else None,
									if (map.contains("fit_notes")) Some(map("fit_notes").toString) else None,
									if (map.contains("material")) Some(map("material").toString) else None,
									if (map.contains("care_instructions")) Some(map("care_instructions").toString) else None,
									if (map.contains("origin")) Some(map("origin").toString) else None)
	}
	
	
}