package models

import com.mongodb.casbah.Imports._
import org.bson.types.ObjectId
import org.json4s.mongo.JObjectParser._

case class Post(_id: ObjectId = new ObjectId, title: String, body: String, authorId: String, createdDate: java.util.Date, updatedDate: java.util.Date)

object Post extends MongoDBModel[Post] {

  override implicit val collectionName: String = "posts"

  def find: Seq[Post] = {
    collection.find(("title" $regex ".*タイトル2.*")).map { post =>
      serialize(post).camelizeKeys.extract[Post]
    }.toSeq
  }

}

