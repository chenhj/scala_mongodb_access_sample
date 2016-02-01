package models

import com.mongodb.casbah.{MongoClient, MongoClientURI}


/**
  * Created by R1 on 2016/01/28.
  */
trait DBManager {
  def collection(name: String) = MongoClient(MongoClientURI("mongodb://192.168.99.100:27017/"))("sports")(name)
}
object DBManager extends DBManager
