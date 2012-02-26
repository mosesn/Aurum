package org.aurum

case class SKU(val id: Number, val inventory_status: String, val msrp_price: String,
                  val sale_price: String, val shipping_surcharge: Option[String],
                  val attributes: Option[Map[String, String]])

object SKU {
  def apply(x: Map[String, Any]): SKU ={
    SKU(x("id").asInstanceOf[Number],
	   x("inventory_status").toString,
	   x("msrp_price").toString,
	   x("sale_price").toString,
	   if (x.contains("shipping_surcharge")) Some(x("shipping_surcharge").toString) else None,
	   if (x.contains("attributes")) Some(makeMap(x("attributes").asInstanceOf[List[Map[String,String]]])) else None)
  }

  private[this] def makeMap(lst: List[Map[String, String]]): Map[String, String] = {
    Map(lst map (elt => (elt("name"), elt("value"))): _*)
  }

}
