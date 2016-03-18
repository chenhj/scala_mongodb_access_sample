package models

import com.mongodb.casbah.Imports._
import org.bson.types.ObjectId

case class Post(_id: ObjectId = new ObjectId, title: String, body: String, authorId: String, createdDate: java.util.Date, updatedDate: java.util.Date)

object Post extends MongoDBModel[Post] {

  override implicit val collectionName: String = "posts"

  def findByTitle(title: String): Seq[Post] = {
    collection.find("title" $regex s".*${title}.*").map { post => modelExtract(post) }.toSeq
  }

}

