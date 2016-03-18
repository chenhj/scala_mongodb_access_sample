package models

import com.mongodb.ServerAddress
import com.mongodb.casbah.{MongoClient, MongoClientOptions, MongoCollection}

trait MongoDBManager {
  private final val URL: String = "192.168.99.100"
  private final val PORT: Int = 27017
  private final val DB: String = "main"
  private val serverAddress: ServerAddress = new ServerAddress(URL, PORT)

  val options = MongoClientOptions(socketKeepAlive = true, connectionsPerHost = 30, maxConnectionIdleTime = 180000, maxConnectionLifeTime = 3000000)
  val client: MongoClient = MongoClient(serverAddress, options)

  def collection(collectionName: String): MongoCollection = client(DB)(collectionName)

  def close(): Unit = {
    client.close()
    println("Connection was closed")
  }
}

object MongoDBManager extends MongoDBManager
