case class SKUObj(val id: Number, val inventory_status: String, val msrp_price: String, val sale_price: String, 
									val shipping_surcharge: Option[String], val attributes: List[AttributeObj])