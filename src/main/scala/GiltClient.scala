import dispatch._
import dispatch.liftjson.Js._

import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.JsonAST.JField
import net.liftweb.json.JsonAST.JArray
import net.liftweb.json.JsonAST.JString

class GiltClient(val apiKey: String) {
  def stores = List("women", "men", "kids", "home")

  def active: List[Sale] = {
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/active.json")
    h((req  <<? Map("apikey" -> apiKey)) ># {json =>
      getSale(json)
    })
  }

  def active(store_key: String): List[Sale] = {
    if (!validStore(store_key)) {
      throw new IllegalArgumentException("Not a valid store key: " + store_key)
    }
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/" + store_key + "/active.json")
    h(req <<? Map("apikey" -> apiKey) ># {json =>
      getSale(json)
    })
  }

  def upcoming: List[Sale] = {
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/upcoming.json")
    h((req <<? Map("apikey" -> apiKey)) ># {json =>
      getSale(json)
    })
  }

  def upcoming(store_key: String): List[Sale] = {
    if (!validStore(store_key)) {
      throw new IllegalArgumentException("Not a valid store key: " + store_key)
    }
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/" + store_key + "/upcoming.json")
    h(req <<? Map("apikey" -> apiKey) ># {json =>
      getSale(json)
    })
  }

  def detail(store_key: String, sale_key: String): Sale = {
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/" + store_key + "/" + sale_key + "/detail.json")
    h(req <<? Map("apikey" -> apiKey) ># {json =>
      Sale(json.values.asInstanceOf[Map[String, Any]])
    })
  }

  def detail(product_id: String): Product = {
    val h = new Http
    val req = url(ProperProductDetailURL(product_id))
    h(req <<? Map("apikey" -> apiKey) ># { json =>
      Product(json.values.asInstanceOf[Map[String,Any]])
    })
  }

  def detailFromURL(url: String): Product = {
    url match {
      case ProperProductDetailURL(product_id) => detail(product_id)
      case _ => throw new IllegalArgumentException("Bad URL")
    }
  }

  object ProperProductDetailURL {
    def apply(product_id: String) = "https://api.gilt.com/v1/products/" + product_id + "/detail.json"

    def unapply(url: String): Option[String] = {
      val r = """https://api.gilt.com/v1/products/(\d+)/detail.json""".r
      url match {
        case r(product_id) => Some(product_id)
        case _ => None
      }
    }
  }

  private[this] def getSale(j: JValue): List[Sale] = {
    val innerLst = j.values.asInstanceOf[Map[String, List[Map[String, Any]]]]("sales")
    innerLst map (elt => Sale(elt))
  }

  private[this] def validStore(store: String): Boolean = stores contains store
}

