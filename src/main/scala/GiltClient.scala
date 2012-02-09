import dispatch._
import dispatch.liftjson.Js._

import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.JsonAST.JField
import net.liftweb.json.JsonAST.JArray
import net.liftweb.json.JsonAST.JString

class GiltClient(val apiKey: String) {
  def stores = List("women", "men", "kids", "home")

  def active: List[SaleObject] = {
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/active.json")
    h((req  <<? Map("apikey" -> apiKey)) ># {json =>
      getSaleObject(json)
    })
  }

  def active(store_key: String): List[SaleObject] = {
    if (!validStore(store_key)) {
      throw new IllegalArgumentException("Not a valid store key: " + store_key)
    }
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/" + store_key + "/active.json")
    h(req <<? Map("apikey" -> apiKey) ># {json =>
      getSaleObject(json)
    })
  }

  def upcoming: List[SaleObject] = {
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/upcoming.json")
    h((req <<? Map("apikey" -> apiKey)) ># {json =>
      getSaleObject(json)
    })
  }

  def upcoming(store_key: String): List[SaleObject] = {
    if (!validStore(store_key)) {
      throw new IllegalArgumentException("Not a valid store key: " + store_key)
    }
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/" + store_key + "/upcoming.json")
    h(req <<? Map("apikey" -> apiKey) ># {json =>
      getSaleObject(json)
    })
  }

  def detail(store_key: String, sale_key: String): SaleObject = {
    val h = new Http
    val req = url("https://api.gilt.com/v1/sales/" + store_key + "/" + sale_key + "/detail.json")
    h(req <<? Map("apikey" -> apiKey) ># {json =>
      SaleObject(json.values.asInstanceOf[Map[String, Any]])
    })
  }

  private[this] def getSaleObject(j: JValue): List[SaleObject] = {
    val innerLst = j.values.asInstanceOf[Map[String, List[Map[String, Any]]]]("sales")
    innerLst map (elt => SaleObject(elt))
  }

  private[this] def validStore(store: String): Boolean = stores contains store
}

