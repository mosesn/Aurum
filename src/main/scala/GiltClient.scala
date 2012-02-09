import dispatch._
import dispatch.liftjson.Js._

import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.JsonAST.JField
import net.liftweb.json.JsonAST.JArray
import net.liftweb.json.JsonAST.JString

class GiltClient(val apiKey: String) {
  def stores = List("women", "men", "kids", "home")

  def getActive: String = {
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/active.json") <<? Map("apikey" -> apiKey)
    val tmp = h(req ># {json =>
      getSaleObject(json)
    })
    "ads"
  }

  def getSaleObject(j: JValue): List[SaleObject] = {
    val lst = j \ "sales"
//    val lst = j.values.asInstanceOf
    lst match {
      case JArray(innerLst) => {
        innerLst map (elt => SaleObject(elt.values.asInstanceOf[Map[String, Any]]))
      }
      case _ => null
    }
  }
}

case class SaleObject(val name: String, val sale: String, val sale_key: String, val store: String,
                 val description: Option[String], val sale_url: String, val begins: String,
                 val ends: Option[String], val image_urls: List[ImageObj], val products: Option[List[String]])

object SaleObject {
  def apply(j: Map[String, Any]): SaleObject ={
    SaleObject((j("name")).toString,
               (j("sale")).toString,
               (j("sale_key")).toString,
               (j("store")).toString,
               if (j.contains("description")) Some(j("description").toString) else None,
               (j("sale_url")).toString,
               (j("begins")).toString,
               if (j.contains("ends")) Some(j("ends").toString) else None,
               getImages(j("image_urls").asInstanceOf[Map[String, List[Map[String, Any]]]]),
               if (j.contains("products")) Some(j("products").asInstanceOf[List[String]]) else None)
  }

  def getImages(map: Map[String, List[Map[String, Any]]]): List[ImageObj] = {
    (map.values.flatten map ({x: Map[String, Any] => makeImageObj(x) })).toList
  }

  def makeImageObj(x: Map[String, Any]) = ImageObj(x("url").toString, x("width").asInstanceOf[BigInt].toInt, x("height").asInstanceOf[BigInt].toInt)
}


case class ImageObj(val url: String, val width: Int, val height: Int)
