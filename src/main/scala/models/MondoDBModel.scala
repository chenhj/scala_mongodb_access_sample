package models

import com.mongodb.casbah.Imports._
import org.bson.types.ObjectId
import org.json4s.DefaultFormats
import org.json4s.mongo.JObjectParser._
import org.json4s.mongo.{DateSerializer, ObjectIdSerializer}

abstract class MongoDBModel[T](implicit m: Manifest[T]) {

  implicit val collectionName: String

  implicit val formats = DefaultFormats + new ObjectIdSerializer + new DateSerializer()

  def collection: MongoCollection = MongoDBManager.collection(collectionName)

  def modelExtract(t: Any) = serialize(t).camelizeKeys.extract[T]

  def extract: Any => T = (t: Any) => modelExtract(t)

  /**
    * 全件取得
    *
    * @return
    */
  def findAll: Seq[T] = collection.find().map(t => modelExtract(t)).toSeq

  /**
    * IDで検索
    *
    * @param id
    * @return
    */
  def findById(id: ObjectId): Option[T] = collection.findOneByID(id).map(t => modelExtract(t))

  /**
    * 複数IDで検索
    *
    * @param ids
    * @return
    */
  def findByIds(ids: Seq[ObjectId]): Seq[T] = collection.find("_id" $in ids).map(t => modelExtract(t)).toSeq

  /**
    * カウント
    *
    * @return
    */
  def count(): Int = collection.count()

  def close(): Unit = MongoDBManager.close()
}
