import models.Post
import org.bson.types.ObjectId
import org.scalatest.{ShouldMatchers, FunSuite}

/**
  * 初めてのScalaTest
  */
class PostTest extends FunSuite with ShouldMatchers {

  test("Find All") {
    assert(Post.findAll.size > 0)
  }

  test("should macher test") {
    Post.findById(new ObjectId("56ac38ece70b03a1b56a36c6")).size should be (1)
  }
}
