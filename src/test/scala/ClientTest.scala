import org.scalatest.FunSuite

class ClientTest extends FunSuite {

  test("Active sales are received properly") {
    val client = new GiltClient(Secret.password)
    val tmp = client.active
    assert(!(tmp isEmpty))
  }

}
