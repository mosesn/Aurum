import org.scalatest.FunSuite

class ClientTest extends FunSuite {

  test("Active sales are received properly") {
    val client = new GiltClient(Secret.password)
    val tmp = client.getActive
    println(tmp)
    assert(!(tmp isEmpty))
  }

}
