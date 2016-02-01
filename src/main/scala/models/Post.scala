package models

import com.mongodb.casbah.Imports._
import org.bson.types.ObjectId
import org.json4s.DefaultFormats
import org.json4s.mongo._
import org.json4s.mongo.JObjectParser._

/**
  * Created by R1 on 2016/01/28.
  */
case class Post(_id: ObjectId = new ObjectId, title: String, body: String, authorId: String, createdDate: java.util.Date, updatedDate: java.util.Date)

object Post {

  implicit val formats = DefaultFormats + new ObjectIdSerializer() + new DateSerializer()

  val collection = DBManager.collection("post")

  def findAll:Seq[Post] = {
    collection.find().map { post =>
      serialize(post).camelizeKeys.extract[Post]
    }.toSeq
  }

  def count:Int = {
    collection.count()
  }

  def findById(id: ObjectId):Option[Post] = {
    collection.findOneByID(id).map { post =>
      serialize(post).camelizeKeys.extract[Post]
    }
  }

  def find:Seq[Post] = {
    collection.find(("title" $regex ".*タイトル2.*")).map { post =>
      serialize(post).camelizeKeys.extract[Post]
    }.toSeq
  }
}

