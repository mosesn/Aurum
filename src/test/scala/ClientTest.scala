import org.scalatest.FunSuite

class ClientTest extends FunSuite {

  test("Active sales are received properly") {
    val client = new GiltClient(Secret.password)
    val tmp = client.active
    assert(!(tmp isEmpty))
  }

  test("Active sales are received properly for all stores") {
    val client = new GiltClient(Secret.password)
    for (store <- client.stores) {
      val tmp = client.active(store)
      assert(!(tmp isEmpty))
    }
  }

  test("Upcoming sales are received properly") {
    val client = new GiltClient(Secret.password)
    val tmp = client.upcoming
    assert(!(tmp isEmpty))
  }

  test("Upcoming sales are received properly for all stores") {
    val client = new GiltClient(Secret.password)
    for (store <- client.stores) {
      val tmp = client.upcoming(store)
      assert(!(tmp isEmpty))
    }
  }

  ignore("Sales details are working") {
    val client = new GiltClient(Secret.password)
    val sales = client.active
    var lst: List[Sale] = Nil
    for (sale <- sales) {
      lst = client.detail(sale.store, sale.sale_key) :: lst
    }
    assert(!(lst isEmpty))
  }

  test("Product details are working") {
    val client = new GiltClient(Secret.password)
    val sales = client.active
    var lst: List[Product] = Nil
    for (sale <- sales) {
      sale.products match {
        case Some(products) => {
          val product = products.head
          lst = client.detailFromURL(product) :: lst
        }
        case None =>
      }
    }
    assert(!(lst isEmpty))
  }
}
