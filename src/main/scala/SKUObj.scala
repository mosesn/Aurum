case class SKUObj(val id: Number, val inventory_status: String, val msrp_price: String,
                  val sale_price: String, val shipping_surcharge: Option[String],
                  val attributes: Map[String, String])

object SKUObj {
	def apply(x: Map[String, Any]): SKUObj ={
		SKUObj(x("id").asInstanceOf[Number], 
		x("inventory_status").toString,
		x("msrp_price").toString,
		x("sale_price").toString,
		if (x.contains("shipping_surcharge")) Some(x("shipping_surcharge").toString) else None,
		if (x.contains("attributes")) Some(x("attributes").asInstanceOf[Map[String,String]]) else None)
	}
}